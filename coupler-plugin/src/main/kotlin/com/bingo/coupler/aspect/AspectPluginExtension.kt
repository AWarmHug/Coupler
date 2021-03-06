package com.bingo.coupler.aspect

import com.bingo.coupler.getClassName
import org.gradle.api.provider.ListProperty

abstract class AspectPluginExtension {

    abstract val packages: ListProperty<String>

    abstract val excludes: ListProperty<String>

    init {
        packages.convention(mutableListOf())
        excludes.convention(mutableListOf())
    }


    fun filter(path: String): Boolean {
        var f = true
        excludes.get().forEach {
            if (getClassName(path).contains(getClassName(it))) {
                f = false
            }
        }
        return f
    }

}