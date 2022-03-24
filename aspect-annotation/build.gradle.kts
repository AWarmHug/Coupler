plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    `maven-publish`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

group = "com.github.AWarmHug"
version = "0.0.2"
publishing {
    repositories {
        maven(url = "../repository")
    }
    publications {
        create<MavenPublication>("aspect-annotation") {
            from(components["java"])
        }
    }
}



