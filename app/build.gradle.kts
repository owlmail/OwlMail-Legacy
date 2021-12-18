plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "github.sachin2dehury.owlmail"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            multiDexEnabled = true
            isDebuggable = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false
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
    buildFeatures {
        viewBinding = true
    }
    packagingOptions {
        resources.excludes.addAll(
            listOf(
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/ASL2.0",
                "META-INF/*.kotlin_module"
            )
        )
    }
}

hilt {
    enableExperimentalClasspathAggregation = true
}

kapt {
    correctErrorTypes = true
}

dependencies {

    //Defaults
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.40.5")
    kapt("com.google.dagger:hilt-compiler:2.40.5")

    // Swipe Refresh Layout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0-RC2")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.0-beta02")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.0-beta02")

    //OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    //RetroFit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Moshi
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")

    // Activity KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.4.0")

    // Room
    implementation("androidx.room:room-runtime:2.4.0-rc01")
    kapt("androidx.room:room-compiler:2.4.0-rc01")
    implementation("androidx.room:room-ktx:2.4.0-rc01")

    // Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //JSoup
    implementation("org.jsoup:jsoup:1.14.3")

    //Google Play Core
    implementation("com.google.android.play:core:1.10.2")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:26.2.0"))
    implementation("com.google.firebase:firebase-messaging-ktx:23.0.0")
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.2.6")
    implementation("com.google.firebase:firebase-analytics-ktx:20.0.1")

    //Ads
    implementation("com.google.android.gms:play-services-ads:20.5.0")

    //Preference
    implementation("androidx.preference:preference-ktx:1.2.0-beta01")

    //Paging Library
    implementation("androidx.paging:paging-runtime-ktx:3.1.0")

    //Epoxy
    implementation("com.airbnb.android:epoxy:4.6.4")
    implementation("com.airbnb.android:epoxy-paging3:4.6.4")

    //Stetho
    implementation("com.facebook.stetho:stetho:1.6.0")

    //Chucker
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    //Leak Canary
//    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.7")

    //Lottie
//    implementation("com.airbnb.android:lottie:4.1.0")

    //DeepLink Dispatch
//    implementation("com.airbnb:deeplinkdispatch:5.4.3")
//    kapt("com.airbnb:deeplinkdispatch-processor:5.4.3")

    //Coil
    implementation("io.coil-kt:coil-base:2.0.0-alpha05")
    implementation("io.coil-kt:coil-svg:2.0.0-alpha05")
    implementation("io.coil-kt:coil-gif:2.0.0-alpha05")
    implementation("io.coil-kt:coil-video:2.0.0-alpha05")

    //ViewPager 2
    implementation("androidx.viewpager2:viewpager2:1.1.0-beta01")

    //Circular Loader
//    implementation("com.mikhaellopez:circularfillableloaders:1.4.0")

    //BackPack Theme
    implementation("net.skyscanner.backpack:backpack-android:33.2.0")

    //Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}