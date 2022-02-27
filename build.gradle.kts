// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")

        // Dagger Hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.41")

        // Safe Args
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0-alpha03")

        // Google Services
        classpath("com.google.gms:google-services:4.3.10")

        // Firebase Crashlytics
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.8.1")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
