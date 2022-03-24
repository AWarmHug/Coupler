package com.bingo.coupler.aspect

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.bingo.coupler.ext.CouplerPluginExtension
import com.bingo.coupler.getClassName
import com.google.common.collect.Sets
import javassist.ClassPool
import org.apache.commons.codec.digest.DigestUtils
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import java.io.File
import java.io.FileInputStream
import java.util.jar.JarFile

class AspectTransform(val project: Project, val extension: AspectPluginExtension) : Transform() {
    override fun getName(): String = "AspectJTransform"

    override fun getInputTypes(): Set<QualifiedContent.ContentType> {
        return Sets.immutableEnumSet(QualifiedContent.DefaultContentType.CLASSES)

    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope>? {
        return Sets.immutableEnumSet(
            QualifiedContent.Scope.PROJECT, QualifiedContent.Scope.EXTERNAL_LIBRARIES,
            QualifiedContent.Scope.SUB_PROJECTS, QualifiedContent.Scope.EXTERNAL_LIBRARIES,
            QualifiedContent.Scope.EXTERNAL_LIBRARIES
        )
    }

    override fun isIncremental(): Boolean = false


    override fun transform(transformInvocation: TransformInvocation) {
        super.transform(transformInvocation)
        val inputs = transformInvocation.inputs
        val outputProvider = transformInvocation.outputProvider
        outputProvider.deleteAll()

        val aspectModels = mutableListOf<AspectModel>()

        val changePackages = extension.packages.get()

        val pool = ClassPool.getDefault()
        val android = project.extensions.findByName("android") as BaseAppModuleExtension
        pool.appendClassPath(android.bootClasspath[0].toString())

        val dirMap = mutableMapOf<String, String>()
        val jarMap = mutableMapOf<File, File>()
        inputs.forEach { input ->
            input.directoryInputs.forEach {
                pool.appendClassPath(it.file.absolutePath)
                // 获取output目录
                val dest = outputProvider.getContentLocation(
                    it.name,
                    it.getContentTypes(), it.getScopes(),
                    Format.DIRECTORY
                )
                dirMap.put(it.file.absolutePath, dest.absolutePath)
            }

            input.jarInputs.forEach {
                pool.appendClassPath(it.file.absolutePath)

                // 重命名输出文件
                var jarName = it.name;
                var md5Name = DigestUtils.md5Hex(it.file.absolutePath);
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length - 4);
                }
                //生成输出路径
                val dest = outputProvider.getContentLocation(
                    jarName + md5Name,
                    it.contentTypes,
                    it.scopes, Format.JAR
                );
                jarMap.put(it.file, dest)
            }
        }


        dirMap.forEach {
            val dir = File(it.key)
            if (dir.isDirectory) {
                dir.walk().forEach { file: File ->
                    if (file.isFile()) {
                        val path = file.absolutePath
                        var contains = false
                        for (pack in changePackages) {
                            val re = getClassName(path)

                            if (re.contains(pack)) {
                                contains = true
                                break
                            }
                        }
                        if (contains) {
                            val reader = ClassReader(FileInputStream(file))
                            val ctClass = pool.get(getClassName(reader.className))
                            aspectModels.add(AspectModel(reader, ctClass))
                        }
                    }
                }
            }
        }
        jarMap.forEach {
            val inJarFile = JarFile(it.key)
            val enumeration = inJarFile.entries();

            while (enumeration.hasMoreElements()) {
                val jarEntry = enumeration.nextElement()
                if (jarEntry == null) {
                    continue
                }
                val entryName = jarEntry.name
                val re = getClassName(entryName)
                var contains = false
                for (pack in changePackages) {
                    if (re.contains(pack)) {
                        contains = true
                        break
                    }
                }

                if (!jarEntry.isDirectory() && contains) {
                    val reader = ClassReader(inJarFile.getInputStream(jarEntry))
                    val ctClass = pool.get(getClassName(reader.className))
                    aspectModels.add(AspectModel(reader, ctClass))
                }
            }
        }


        aspectModels.forEach {

        }

        dirMap.forEach { dir ->
            aspectModels.forEach { aspectModels ->
                aspectModels.injectDir(pool, dir.key, dir.value, extension)
            }
        }


    }
}