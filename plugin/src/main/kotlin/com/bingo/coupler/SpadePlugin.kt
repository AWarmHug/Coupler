package com.bingo.coupler

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.bingo.coupler.ext.SpadePluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.kotlin.dsl.create

class SpadePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.logger.isEnabled(LogLevel.DEBUG)

        val ext = target.extensions.create<SpadePluginExtension>("spade")

        target.extensions.configure<BaseAppModuleExtension>("android") {
            registerTransform(SpadeTransform(target, ext))
        }
    }
}