package com.bingo.coupler.aspect

import android.util.Log
import com.bingo.aspect.annotation.PointCut
import com.bingo.aspect.annotation.TargetClass
import com.bingo.coupler.getClassName
import com.bingo.coupler.log
import com.google.common.io.Files
import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.objectweb.asm.*
import org.objectweb.asm.Opcodes.ASM9
import org.objectweb.asm.commons.InstructionAdapter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


private data class Target(
    val clazz: String,
    val ctMethod: CtMethod,
    val pointClassReader: ClassReader
) {


}


class AspectModel constructor() {
    var ctClassName: String? = null
    val ctMethods = mutableListOf<CtMethod>()
    private val targets = mutableListOf<Target>()


    constructor(classReader: ClassReader, ctClass: CtClass) : this() {
        ctClassName = ctClass.name

        ctClass.methods.forEach {
            if (it.hasAnnotation(TargetClass::class.java)) {
                ctMethods.add(it)
            }
        }



        ctMethods.forEach {

            val targetClass: TargetClass = it.getAnnotation(TargetClass::class.java) as TargetClass


            if (it.hasAnnotation(PointCut::class.java)) {

                val pointCut: PointCut = it.getAnnotation(PointCut::class.java) as PointCut

            }


            val target = Target(targetClass.value, it, classReader)
            targets.add(target)


        }

    }

    fun injectDir(pool: ClassPool, src: String, dest: String, shape: AspectPluginExtension) {

        val dir = File(src)

        if (dir.isDirectory) {
            dir.walk().forEach { file ->
                val filePath = file.absolutePath
                if (file.isFile) {
                    val out = File(dest + filePath.substring(src.length))
                    Files.createParentDirs(out)
                    if (filePath.endsWith(".class") && !filePath.contains('-') && !filePath.contains(
                            "R$"
                        ) && !filePath.contains(
                            "R.class"
                        ) && !filePath.contains(
                            "BuildConfig.class"
                        ) && shape.filter(filePath)
                    ) {

                        val reader = ClassReader(FileInputStream(filePath))

                        targets.forEach {
                            if (getClassName(reader.className) == it.clazz) {
                                //找到相应的方法进行替换

                                val targetCtClass = pool.get(getClassName(reader.className))
                                if (targetCtClass.isFrozen) {
                                    targetCtClass.defrost()
                                }

                                val targetMethod = targetCtClass.getDeclaredMethod(
                                    it.ctMethod.name,
                                    it.ctMethod.parameterTypes
                                )

                                val classWriter = ClassWriter(
                                    it.pointClassReader,
                                    ClassWriter.COMPUTE_FRAMES or ClassWriter.COMPUTE_MAXS
                                )

                                val cv: ClassVisitor = object : ClassVisitor(ASM9, classWriter) {

                                    override fun visit(
                                        version: Int,
                                        access: Int,
                                        name: String?,
                                        signature: String?,
                                        superName: String?,
                                        interfaces: Array<out String>?
                                    ) {
                                        super.visit(
                                            version,
                                            access,
                                            name,
                                            signature,
                                            superName,
                                            interfaces
                                        )
                                        log("visit::::::  version = $version , access = $access , name = $name , signature = $signature , superName = $superName , interfaces = $interfaces")
                                    }

                                    override fun visitField(
                                        access: Int,
                                        name: String?,
                                        descriptor: String?,
                                        signature: String?,
                                        value: Any?
                                    ): FieldVisitor {

                                        log("visitField::::::  access = $access , name = $name , descriptor = $descriptor , signature = $signature , value = $value")



                                        return super.visitField(
                                            access,
                                            name,
                                            descriptor,
                                            signature,
                                            value
                                        )
                                    }

                                    override fun visitMethod(
                                        access: Int,
                                        name: String?,
                                        descriptor: String?,
                                        signature: String?,
                                        exceptions: Array<out String>?
                                    ): MethodVisitor {

                                        log("visitMethod::::::  access = $access , name = $name , descriptor = $descriptor , signature = $signature , exceptions = $exceptions")


                                        val methodVisitor = super.visitMethod(
                                            access,
                                            name,
                                            descriptor,
                                            signature,
                                            exceptions
                                        )
                                        if (name == it.ctMethod.name) {

                                            return object : MethodVisitor(ASM9, methodVisitor) {

                                                override fun visitCode() {
                                                    super.visitCode()
                                                }




                                            }
                                        } else {
                                            return methodVisitor
                                        }
                                    }

                                    override fun visitEnd() {
                                        super.visitEnd()
                                        log("visitEnd::::::  ")

                                    }

                                }

                                it.pointClassReader.accept(
                                    cv,
                                    ClassReader.SKIP_DEBUG or ClassReader.SKIP_FRAMES
                                )

                                val pointClassWriter =
                                    ClassWriter(it.pointClassReader, Opcodes.ASM9)

                                val pointMethodVisitor = pointClassWriter.visitMethod(
                                    it.ctMethod.modifiers,
                                    it.ctMethod.name,
                                    it.ctMethod.methodInfo.descriptor,
                                    null,
                                    arrayOf()
                                )


                                val classReader = ClassReader(FileInputStream(filePath))
                                //读取目标类，进行转换（将point里的代码添加到目标方法中），然后写入指定位置
                                //（2）构建ClassWriter

                                //（2）构建ClassWriter
                                val cw = ClassWriter(ClassWriter.COMPUTE_FRAMES)

                                val aspectClassVisitor =
                                    AspectClassVisitor(it.pointClassReader, it.ctMethod, cw)

                                val parsingOptions =
                                    ClassReader.SKIP_DEBUG or ClassReader.SKIP_FRAMES;

                                classReader.accept(aspectClassVisitor, parsingOptions)

                                val bytes = cw.toByteArray()

//                                IOUtils.write(bytes, FileOutputStream(dest))

                            }
                        }


                    } else {
                        FileUtils.copyFile(file, out)
                    }
                }
            }
        }


    }


}