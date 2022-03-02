plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(gradleApi()) //gradle sdk
    implementation(localGroovy()) //groovy sdk
    implementation("com.android.tools.build:gradle:7.1.1")
    implementation("com.android.tools.build:gradle-api:7.1.1")
    implementation("com.android.tools.build:gradle-core:3.1.4")
    implementation("org.javassist:javassist:3.25.0-GA")
    implementation("com.google.android:android:4.1.1.4")
}

sourceSets {
    main {
//        org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetContainer
        java.srcDirs("src/main/java", "../plugin/src/main/java","../plugin/src/main/kotlin")
        groovy.srcDirs("src/main/groovy", "../plugin/src/main/groovy")
        resources.srcDirs("src/main/resources", "../plugin/src/main/resources")
    }
}


repositories {
    maven {
        setUrl("https://maven.aliyun.com/repository/public")
    }
    maven {
        setUrl("https://maven.aliyun.com/repository/google")
    }
    maven {
        setUrl("https://maven.aliyun.com/repository/gradle-plugin")
    }
    gradlePluginPortal()
    google()
    mavenCentral()
}