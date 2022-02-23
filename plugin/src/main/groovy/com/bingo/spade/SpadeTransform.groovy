package com.bingo.spade

import com.android.build.api.transform.*
import com.bingo.spade.ext.SpadePluginExtension
import com.google.common.collect.Sets
import javassist.ClassPool
import javassist.CtClass
import javassist.JarClassPath
import org.apache.commons.codec.digest.DigestUtils
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.objectweb.asm.ClassReader

import java.util.jar.JarFile

class SpadeTransform extends Transform {
    private SpadePluginExtension extension;
    Project project
    static Logger logger


    SpadeTransform(Project project, SpadePluginExtension extension) {
        this.project = project
        this.extension = extension
        this.logger = project.logger
    }

    @Override
    String getName() {
        return "SpadeTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return Sets.immutableEnumSet(QualifiedContent.DefaultContentType.CLASSES)
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return Sets.immutableEnumSet(QualifiedContent.Scope.PROJECT, QualifiedContent.Scope.EXTERNAL_LIBRARIES,
                QualifiedContent.Scope.SUB_PROJECTS, QualifiedContent.Scope.EXTERNAL_LIBRARIES,
                QualifiedContent.Scope.EXTERNAL_LIBRARIES)
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        Injector.changePackages = extension.packages
        extension.excludes.add(Injector.without)
        extension.excludes.addAll(Injector.changePackages)

        Log.log(" changePackage = ${Injector.changePackages}")
        Log.log(" excludes = ${extension.excludes}")

        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider

        outputProvider.deleteAll()

        ClassPool pool = ClassPool.getDefault()
        pool.appendClassPath(project.android.bootClasspath[0].toString())

        Map<String, String> dirMap = new HashMap<>();
        Map<File, File> jarMap = new HashMap<>();

        inputs.each { TransformInput input ->
            input.directoryInputs.each {
                pool.appendClassPath(it.file.absolutePath)

                // 获取output目录
                File dest = outputProvider.getContentLocation(it.name,
                        it.getContentTypes(), it.getScopes(),
                        Format.DIRECTORY)

                dirMap.put(it.file.absolutePath, dest.absolutePath)

            }

            input.jarInputs.each {JarInput it ->
                def classPath = new JarClassPath(it.file.absolutePath)
                pool.appendClassPath(classPath)

                // 重命名输出文件
                String jarName = it.name;
                String md5Name = DigestUtils.md5Hex(it.file.absolutePath);
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4);
                }
                //生成输出路径
                File dest = outputProvider.getContentLocation(jarName + md5Name,
                        it.getContentTypes(), it.getScopes(), Format.JAR);
                jarMap.put(it.file, dest)
            }
        }

        dirMap.each {

            File dir = new File(it.key)
            if (dir.isDirectory()) {
                dir.eachFileRecurse { File file ->
                    if (file.isFile()) {
                        String path = file.absoluteFile
                        boolean contains = false
                        for (pack in Injector.changePackages) {
                            String re = Utils.getClassName(path)

                            if (re.contains(pack)) {
                                contains = true
                                break
                            }
                        }
                        if (contains) {
                            ClassReader reader = new ClassReader(new FileInputStream(new File(path)))
                            CtClass ctClass = pool.get(Utils.getClassName(reader.className));
                            Injector.clazz.put(ctClass.superclass.name, ctClass.name)
                        }
                    }
                }
            }
        }


        jarMap.each {

            def inJarFile = new JarFile(it.key)

            def enumeration = inJarFile.entries();

            while (enumeration.hasMoreElements()) {
                def jarEntry = enumeration.nextElement()
                if (jarEntry == null) {
                    continue
                }
                def entryName = jarEntry.name
                String re = Utils.getClassName(entryName)
                boolean contains = false
                for (pack in Injector.changePackages) {
                    if (re.contains(pack)) {
                        contains = true
                        break
                    }
                }

                if (!jarEntry.isDirectory() && contains) {
                    ClassReader reader = new ClassReader(inJarFile.getInputStream(jarEntry))
                    CtClass ctClass = pool.get(Utils.getClassName(reader.className));
                    Injector.clazz.put(ctClass.superclass.name, ctClass.name)
                }
            }
        }

        dirMap.each {
            Injector.injectDir(pool, it.key, it.value, extension)
        }


        jarMap.each {
            Injector.injectJar(pool, it.key, it.value, extension)
        }

        println "Track-Plugin-----transform over------"

    }


}