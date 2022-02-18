package com.bingo.spade

import com.bingo.spade.ext.SpadePluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class SpadePlugin implements Plugin<Project> {


    @Override
    void apply(Project project) {
//        TrackExtension trackExt = project.extensions.create("track", TrackExtension, project)
        SpadePluginExtension extension = project.extensions.create("spade", SpadePluginExtension)

        project.task("spade"){
            doLast({
                println("--------Hello from Spade-------")
                println extension.message.get()
            })
        }

        project.android.registerTransform(new SpadeTransform(project, extension))


    }
}


