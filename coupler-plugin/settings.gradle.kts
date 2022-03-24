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
        maven {
            url = uri("../repository")
        }
        google()
        mavenCentral()
    }
}




