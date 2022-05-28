package com.bingo.coupler

import com.bingo.coupler.ext.CouplerPluginExtension
import com.google.common.io.Files
import com.google.gson.Gson
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


class Injector(val trackInfos: Map<String, Map<String, TrackContent>>) {
    val clazz = mutableMapOf<String, String>()
    fun injectDir(pool: ClassPool, src: String, dest: String, shape: CouplerPluginExtension) {
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
                    if (filePath.endsWith(".class")
                        && !filePath.contains("module-info")
                        && !filePath.contains("R$")
                        && !filePath.contains("R.class")
                        && !filePath.contains("BuildConfig.class")
                        && !shape.filter(filePath)
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
                            val trackContents = trackInfos[getClassName(reader.className)]
                            if (trackContents != null) {
                                val targetClass = pool.get(getClassName(reader.className))
                                if (targetClass.isFrozen()) {
                                    targetClass.defrost()
                                }

                                targetClass.declaredMethods.forEach { ctMethod ->
                                    trackContents[ctMethod.name]?.let {
                                        ctMethod.insertBefore(it.content)
                                    }
                                }
                                targetClass.writeFile(dest)
                                targetClass.detach()
                            } else {
                                FileUtils.copyFile(file, out)
                            }
                        }
                    } else {
                        FileUtils.copyFile(file, out)
                    }
                }
            }
        }
    }

    fun injectJar(pool: ClassPool, src: File, dest: File, config: CouplerPluginExtension) {
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

            if (!jarEntry.isDirectory()
                && entryName.endsWith(".class")
                && !entryName.contains("module-info")
                && !entryName.contains("R$")
                && !entryName.contains("R.class")
                && !entryName.contains("BuildConfig.class")
                && !config.filter(entryName)
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
                    val trackContents = trackInfos[reader.className]
                    if (trackContents != null) {
                        val targetClass = pool.get(getClassName(reader.className))
                        if (targetClass.isFrozen()) {
                            targetClass.defrost()
                        }

                        targetClass.declaredMethods.forEach { ctMethod ->
                            trackContents[ctMethod.name]?.let {
                                ctMethod.insertBefore(it.content)
                            }
                        }
                        IOUtils.write(targetClass.toBytecode(), zos)
                        targetClass.detach()
                    } else {
                        IOUtils.copy(inJarFile.getInputStream(jarEntry), zos)
                    }
                }

            } else {
                IOUtils.copy(inJarFile.getInputStream(jarEntry), zos)
            }
        }
        zos.close()

    }


}