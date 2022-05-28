package com.bingo.coupler.ext

import com.bingo.coupler.getClassName
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

abstract class CouplerPluginExtension {

    abstract val packages: ListProperty<String>

    abstract val excludes: ListProperty<String>

    abstract val jsonFilePath: Property<String>


    init {
        packages.convention(mutableListOf())
        excludes.convention(mutableListOf())
        jsonFilePath.convention("./track/track.json")
    }


    fun filter(path: String): Boolean {
        var f = false
        excludes.get().forEach {
            if (getClassName(path).contains(getClassName(it))) {
                f = true
            }
        }
        return f
    }

}