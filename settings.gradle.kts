pluginManagement {
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
        maven {
            url = uri("coupler-plugin/build/repository")
        }
        maven {
            url = uri("repository")
        }
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
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

        google()
        mavenCentral()
    }
}
rootProject.name = "Spade"

includeBuild("coupler-plugin")
include(":app", ":spade")
include(":aspect")
include(":aspect-annotation")
