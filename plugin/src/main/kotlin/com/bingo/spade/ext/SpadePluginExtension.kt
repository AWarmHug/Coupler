package com.bingo.spade.ext

import com.bingo.spade.getClassName

class SpadePluginExtension() {
    var enabled: Boolean = true

    var packages = mutableListOf<String>()

    var excludes = mutableListOf<String>()


    fun filter(path: String): Boolean {
        var f = true
        excludes.forEach {
            if (getClassName(path).contains(getClassName(it))) {
                f = false
            }
        }
        return f
    }

}