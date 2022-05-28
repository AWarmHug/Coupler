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
    implementation("org.javassist:javassist:3.28.0-GA")
    implementation("org.ow2.asm:asm:9.0")
    implementation("com.google.code.gson:gson:2.8.6")
    compileOnly("com.google.android:android:4.1.1.4")
    api("com.github.AWarmHug:aspect-annotation:0.0.2")

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
