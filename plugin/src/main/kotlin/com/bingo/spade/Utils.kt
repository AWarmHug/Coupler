package com.bingo.spade

import java.io.File


fun getClassName(name: String): String {
    return name.replace("\\", ".").replace("/", ".")
}

fun getFileName(name: String): String {
    return name.replace(".", File.separator).replace("\\", File.separator)
        .replace("/", File.separator)
}

fun log(content: String) {
    println("Track ----- ${content}")

}