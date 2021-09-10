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

kapt {
    correctErrorTypes = true
}

dependencies {

    //Defaults
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-compiler:2.38.1")

    // Swipe Refresh Layout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2-native-mt")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")

    //OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    //RetroFit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Moshi
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")

    // Activity KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.3.1")

    // Room
    implementation("androidx.room:room-runtime:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")
    implementation("androidx.room:room-ktx:2.3.0")

    // Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //JSoup
    implementation("org.jsoup:jsoup:1.14.2")

    //Google Play Core
    implementation("com.google.android.play:core:1.10.1")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:26.2.0"))
    implementation("com.google.firebase:firebase-messaging-ktx:22.0.0")
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.2.1")
    implementation("com.google.firebase:firebase-analytics-ktx:19.0.1")

    //Ads
    implementation("com.google.android.gms:play-services-ads:20.3.0")

    //Preference
    implementation("androidx.preference:preference-ktx:1.1.1")

    //Paging Library
    implementation("androidx.paging:paging-runtime-ktx:3.0.1")

    //Epoxy
    implementation("com.airbnb.android:epoxy:4.6.3")
    implementation("com.airbnb.android:epoxy-paging3:4.6.3")

    //Stetho
    implementation("com.facebook.stetho:stetho:1.6.0")

    //Chucker
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    //Leak Canary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.7")

    //Lottie
    implementation("com.airbnb.android:lottie:4.1.0")

    //DeepLink Dispatch
    implementation("com.airbnb:deeplinkdispatch:5.4.3")
    kapt("com.airbnb:deeplinkdispatch-processor:5.4.3")

    //Coil
    implementation("io.coil-kt:coil-base:1.3.2")
    implementation("io.coil-kt:coil-svg:1.3.2")
    implementation("io.coil-kt:coil-gif:1.3.2")
    implementation("io.coil-kt:coil-video:1.3.2")

    //ViewPager 2
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    //Circular Loader
    implementation("com.mikhaellopez:circularfillableloaders:1.4.0")

    //Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}