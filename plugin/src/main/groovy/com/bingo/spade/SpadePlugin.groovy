package com.bingo.spade

import com.bingo.spade.ext.SpadePluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class SpadePlugin implements Plugin<Project> {


    @Override
    void apply(Project project) {
        SpadePluginExtension extension = project.extensions.create("spade", SpadePluginExtension)

        project.android.registerTransform(new SpadeTransform(project, extension))



    }
}


