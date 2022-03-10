plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

dependencies {

    implementation(gradleApi()) //gradle sdk
    implementation(localGroovy()) //groovy sdk
    implementation("com.android.tools.build:gradle:7.1.1")
    implementation("com.android.tools.build:gradle-api:7.1.1")
    implementation("com.android.tools.build:gradle-core:3.1.4")
    implementation("org.javassist:javassist:3.28.0-GA")
    implementation("com.google.android:android:4.1.1.4")
}

publishing {
    repositories {
        maven(url = "build/repository")
    }
}

group = "com.github.AWarmHug"
version = "0.0.2"
gradlePlugin {
    plugins {
        //create or register
        create("coupler") {
            id = "coupler-plugin"
            implementationClass = "com.bingo.coupler.CouplerPlugin"

        }

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