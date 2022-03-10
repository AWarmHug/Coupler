//https://www.wenjiangs.com/docs/gradle-user-guide
import com.bingo.coupler.ext.CouplerPluginExtension

//import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("spade-plugin")
}

apply {
    plugin(com.bingo.coupler.CouplerPlugin::class.java)
}

extensions.configure<CouplerPluginExtension>("spade") {
    packages.addAll("com.bingo.spadedemo.spade.widget", "com.bingo.spadedemo.spade.activity")
    excludes.addAll("androidx.activity")
}

//spade {
//    packages.addAll("com.bingo.spadedemo.spade.widget", "com.bingo.spadedemo.spade.activity")
//    excludes.addAll("androidx.activity")
//}


android {
    compileSdk = 31
    dataBinding.isEnabled = true

    defaultConfig {
        applicationId = "com.bingo.spadedemo"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation(project(":spade"))
    implementation(project(":aspect"))

    implementation("com.github.bumptech.glide:glide:4.13.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.0")
}