package com.bingo.coupler.ext

import com.bingo.coupler.getClassName
import org.gradle.api.provider.ListProperty

abstract class CouplerPluginExtension {

    abstract val packages: ListProperty<String>

    abstract val excludes: ListProperty<String>

    init {
        packages.convention(mutableListOf())
        excludes.convention(mutableListOf())
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