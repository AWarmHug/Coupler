package com.bingo.spade

import com.bingo.spade.ext.SpadePluginExtension
import com.google.common.io.Files
import javassist.ClassPool
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.objectweb.asm.ClassReader

import java.util.jar.JarFile
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class Injector {


    public static List<String> PACKAGE_WIDGET

    public static final String without = "com${File.separator}bingo${File.separator}spade"

    public static Map<String, String> clazz = new HashMap<>()

    static injectDir(ClassPool pool, String src, String dest, SpadePluginExtension config) {
        println "------------------"
        println("src = ${src}")
        println "dest = ${dest}"

        File dir = new File(src);

        if (dir.isDirectory()) {
            dir.eachFileRecurse { File file ->
                String filePath = file.absolutePath

                if (file.isFile()) {
                    File out = new File(dest + filePath.substring(src.length()))
                    Files.createParentDirs(out)
                    if (filePath.endsWith(".class") && !filePath.contains('-') && !filePath.contains('R$') && !filePath.contains('R.class') && !filePath.contains('BuildConfig.class') && config.filter(filePath)) {
                        println "--------正在转换 Dir 中的Class ----------"
                        println("classPath = ${filePath}")

                        ClassReader reader = new ClassReader(new FileInputStream(filePath))
                        String superClassName = Utils.getClassName(reader.superName)

                        String value = clazz.get(superClassName)

                        if (value != null) {
                            def itemViewCtClass = pool.get(Utils.getClassName(reader.className))
                            if (itemViewCtClass.isFrozen()) {
                                itemViewCtClass.defrost()
                            }
                            itemViewCtClass.superclass = pool.get(value);
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

    static injectJar(ClassPool pool, String src, String dest, SpadePluginExtension config) {

        println "------------------"
        println "src = ${src}"
        println "dest = ${dest}"

        Files.createParentDirs(new File(dest))
//        ZipInputStream zis = new ZipInputStream(new FileInputStream(src))

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));

        def inJarFile = new JarFile(src)

        def enumeration = inJarFile.entries();

        while (enumeration.hasMoreElements()) {
            def jarEntry = enumeration.nextElement()
            if (jarEntry == null) {
                continue
            }
            def entryName = jarEntry.name

            zos.putNextEntry(new ZipEntry(entryName))

            if (!jarEntry.isDirectory() && !entryName.contains('-') && entryName.endsWith(".class") && !entryName.contains('R$') && !entryName.contains('R.class') && !entryName.contains('BuildConfig.class') && config.filter(entryName)) {

                println "--------正在转换 Jar 中的Clase----------"
                println("classPath = ${entryName}")

                ClassReader reader = new ClassReader(inJarFile.getInputStream(jarEntry))
                String superClassName = Utils.getClassName(reader.superName)

                String value = clazz.get(superClassName)

                if (value != null) {
                    def itemViewCtClass = pool.get(Utils.getClassName(reader.className))
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
//        zis.close()
        zos.close()
    }


}