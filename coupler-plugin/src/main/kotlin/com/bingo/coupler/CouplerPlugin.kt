package com.bingo.coupler

import com.android.build.gradle.internal.cxx.json.PlainFileGsonTypeAdaptor
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.bingo.coupler.ext.CouplerPluginExtension
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.kotlin.dsl.create
import java.io.File
import java.lang.reflect.Type

fun <T> readJsonFile(file: File, type: Type): T {
    return GsonBuilder()
        .registerTypeAdapter(File::class.java, PlainFileGsonTypeAdaptor())
        .create()
        .fromJson(file.readText(), type)
}


class CouplerPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.logger.isEnabled(LogLevel.DEBUG)

        val couplerExt = target.extensions.create<CouplerPluginExtension>("coupler")

//        val aspectExt = target.extensions.create<AspectPluginExtension>("aspect")


        target.extensions.configure<BaseAppModuleExtension>("android") {

            val jsonFile = target.file(couplerExt.jsonFilePath.get())
            target.logger.log(LogLevel.ERROR,"jsonFile = ${jsonFile.absolutePath}")

            if (jsonFile.isFile) {
                val type: Type =
                    object : TypeToken<Map<String, Map<String, TrackContent>>>() {}.type
                val trackInfos: Map<String, Map<String, TrackContent>> =
                    readJsonFile(jsonFile, type)
                println("target : $target")
                registerTransform(CouplerTransform(target, couplerExt, trackInfos))
            }else{
                target.logger.log(LogLevel.ERROR,"JsonFile is error!!!")
            }
//            registerTransform(AspectTransform(target,aspectExt))
        }
    }
}