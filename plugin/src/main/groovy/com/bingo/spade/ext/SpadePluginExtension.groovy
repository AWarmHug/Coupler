package com.bingo.spade.ext

import org.gradle.api.provider.Property


 class SpadePluginExtension {


    boolean enabled

    List<String> excludePackages = new ArrayList<>()

    SpadePluginExtension() {

    }

    boolean filter(String path) {
        boolean f = true;
        excludePackages.each {
            if (Utils.getFileName(path).contains(Utils.getFileName(it))) {
                f = false
            }
        }
        return f
    }

    @Override
    public String toString() {
        return "TrackConfig{" +
                "enabled=" + enabled +
                ", excludes=" + excludePackages +
                '}';
    }
}

