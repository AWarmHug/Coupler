package com.bingo.spade.ext

import com.bingo.spade.Utils

class SpadePluginExtension {


    boolean enabled

    List<String> packages

    List<String> excludes = new ArrayList<>()

    SpadePluginExtension() {

    }

    boolean filter(String path) {
        boolean f = true;
        excludes.each {
            if (Utils.getFileName(path).contains(Utils.getFileName(it))) {
                f = false
            }
        }
        return f
    }

}

