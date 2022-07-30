// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")

        // Dagger Hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.41")

        // Safe Args
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0-alpha04")

        // Google Services
        classpath("com.google.gms:google-services:4.3.10")

        // Firebase Crashlytics
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.8.1")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

plugins {
    id("com.osacky.doctor") version "0.8.0"
//    id("scabbard.gradle") version "0.5.0"
}

// scabbard {
//    enabled = true
//    outputFormat = "svg"
// }

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
