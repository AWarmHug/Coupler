package com.bingo.spade

import com.bingo.spade.ext.SpadePluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.logging.LogLevel


class SpadePlugin implements Plugin<Project> {


    @Override
    void apply(Project project) {
        project.logger.isEnabled(LogLevel.DEBUG)
        SpadePluginExtension extension = project.extensions.create("spade", SpadePluginExtension)

        project.getExtensions().configure("android", { BaseAppModuleExtension it ->
            it.registerTransform(new SpadeTransform(project, extension))
        })
    }
}


