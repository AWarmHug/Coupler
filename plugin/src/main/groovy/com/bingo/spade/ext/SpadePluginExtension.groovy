package com.bingo.spade.ext

import org.gradle.api.provider.Property


abstract class SpadePluginExtension {

    abstract Property<String> getMessage()

    boolean enabled
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

    @Override
    public String toString() {
        return "TrackConfig{" +
                "enabled=" + enabled +
                ", excludes=" + excludes +
                '}';
    }
}

