package com.bingo.coupler

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.bingo.coupler.ext.CouplerPluginExtension
import com.bingo.coupler.aspect.AspectPluginExtension
import com.bingo.coupler.aspect.AspectTransform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.kotlin.dsl.create

class CouplerPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.logger.isEnabled(LogLevel.DEBUG)

        val couplerExt = target.extensions.create<CouplerPluginExtension>("coupler")

//        val aspectExt = target.extensions.create<AspectPluginExtension>("aspect")


        target.extensions.configure<BaseAppModuleExtension>("android") {
            registerTransform(CouplerTransform(target, couplerExt))
//            registerTransform(AspectTransform(target,aspectExt))
        }
    }
}