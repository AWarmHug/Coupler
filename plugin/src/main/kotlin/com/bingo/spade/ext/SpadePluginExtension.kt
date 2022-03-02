package com.bingo.spade.ext

import com.bingo.spade.getClassName
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

abstract class SpadePluginExtension {

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