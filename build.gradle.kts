// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version ("7.1.1") apply false
    id("com.android.library") version "7.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.10" apply false
    id("spade-plugin") version "0.0.2" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

//tasks {
//
//    val plugin by registering(GradleBuild::class) {
//        dir = file("plugin")
//        tasks = listOf("publish")
//    }
//
//    val app by registering(GradleBuild::class) {
//        dir = file("app")
//        tasks = listOf("myCopyTask")
//    }
//
//    app {
//        dependsOn(plugin)
//    }
//}
