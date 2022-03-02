package com.bingo.spade

import com.bingo.spade.ext.SpadePluginExtension
import com.google.common.io.Files
import javassist.ClassPool
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.objectweb.asm.ClassReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.jar.JarFile
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream


class Injector() {
    val clazz = mutableMapOf<String, String>()
    fun injectDir(pool: ClassPool, src: String, dest: String, shape: SpadePluginExtension) {
        log("------Dir------------------------------------------------------------------------------------------")
        log("src = ${src}")
        log("dest = ${dest}")

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
                        val superClassName = getClassName(reader.superName)

                        val value = clazz.get(superClassName)

                        if (value != null) {
                            val itemViewCtClass = pool.get(getClassName(reader.className))
                            log("classPath = ${filePath}")
                            log("------ change ${reader.className}----------")
                            log("------ oldSuperClass= ${itemViewCtClass.superclass.getName()}----------")
                            log("------ newSuperClass= ${pool.get(value).getName()}----------")

                            if (itemViewCtClass.isFrozen()) {
                                itemViewCtClass.defrost()
                            }
                            itemViewCtClass.superclass = pool.get(value)
                            itemViewCtClass.writeFile(dest)
//                            IOUtils.write(itemViewCtClass.toBytecode(),new FileOutputStream(dest + filePath.substring(src.length())))
                            itemViewCtClass.detach()
                        } else {
//                            if (superClassName == "androidx.fragment.app.Fragment") {
//                                def fragment = pool.get(Utils.getClassName(reader.className))
//                                if (fragment.isFrozen()) {
//                                    fragment.defrost()
//                                }
//                                CtMethod ctMethod = fragment.getDeclaredMethod("onViewCreated", pool.get(["android.view.View", "android.os.Bundle"] as String[]))
//                                ctMethod.insertAfter("\$1.setTag(com.bingo.track.R.id.key_fragment_name,\"${reader.className}\");")
//                                fragment.writeFile(dest)
//                                fragment.detach()
//                            } else {
                            FileUtils.copyFile(file, out)
//                            }
                        }
                    } else {
                        FileUtils.copyFile(file, out)
                    }
                }
            }
        }
    }

    fun injectJar(pool: ClassPool, src: File, dest: File, config: SpadePluginExtension) {
        log("---Jar----------------------------------------------------------------------------------")
        log("src = ${src}")
        log(
            "dest = ${
                dest.absolutePath
                    .substring(dest.absolutePath.lastIndexOf("intermediates"))
            }"
        )

        Files.createParentDirs(dest)

        //ZipInputStream zis = new ZipInputStream(new FileInputStream(src))
        val zos = ZipOutputStream(FileOutputStream(dest))

        val inJarFile = JarFile(src)

        val enumeration = inJarFile.entries();
        while (enumeration.hasMoreElements()) {
            val jarEntry = enumeration.nextElement()
            if (jarEntry == null) {
                continue
            }
            val entryName = jarEntry.name
            zos.putNextEntry(ZipEntry(entryName))

            if (!jarEntry.isDirectory() && !entryName.contains('-') && entryName.endsWith(".class") && !entryName.contains(
                    "R$"
                ) && !entryName.contains("R.class") && !entryName.contains("BuildConfig.class") && config.filter(
                    entryName
                )
            ) {


                val reader = ClassReader(inJarFile.getInputStream(jarEntry))
                val superClassName = getClassName(reader.superName)

                val value = clazz.get(superClassName)

                if (value != null) {
                    val itemViewCtClass = pool.get(getClassName(reader.className))
                    log("classPath = ${entryName}")
                    log("------ change ${reader.className}----------")
                    log("------ oldSuperClass= ${itemViewCtClass.superclass.getName()} ----------")
                    log("------ newSuperClass= ${pool.get(value).getName()}----------")

                    if (itemViewCtClass != null) {
                        if (itemViewCtClass.isFrozen()) {
                            itemViewCtClass.defrost()
                        }
                        itemViewCtClass.superclass = pool.get(value)
                        IOUtils.write(itemViewCtClass.toBytecode(), zos)
                        itemViewCtClass.detach()
                    }
                } else {
                    IOUtils.copy(inJarFile.getInputStream(jarEntry), zos)
                }

            } else {
                IOUtils.copy(inJarFile.getInputStream(jarEntry), zos)
            }
        }
        zos.close()

    }


}