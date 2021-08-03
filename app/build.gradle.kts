plugins {
    id("com.android.application")
    id("kotlin-android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("androidx.navigation.safeargs.kotlin")
    id ("com.google.gms.google-services")
    id ("com.google.firebase.crashlytics")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId= "github.sachin2dehury.owlmail"
        minSdk =21
        targetSdk= 31
        versionCode= 1
        versionName ="1.0"

        testInstrumentationRunner= "androidx.test.runner.AndroidJUnitRunner"
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
        exclude ("META-INF/DEPENDENCIES")
        exclude ("META-INF/LICENSE")
        exclude ("META-INF/LICENSE.txt")
        exclude ("META-INF/license.txt")
        exclude ("META-INF/NOTICE")
        exclude ("META-INF/NOTICE.txt")
        exclude ("META-INF/notice.txt")
        exclude ("META-INF/ASL2.0")
        exclude ("META-INF/*.kotlin_module")
    }
}

kapt {
    correctErrorTypes =  true
}

dependencies {

    //Defaults
    implementation ("androidx.core:core-ktx:1.6.0")
    implementation ("androidx.appcompat:appcompat:1.3.1")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.0.4")

    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.38.1")
    kapt ("com.google.dagger:hilt-compiler:2.38.1")

    // Swipe Refresh Layout
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    // Navigation Component
    implementation ("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.3.5")

    //OkHttp
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    //RetroFit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    //Moshi
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.moshi:moshi-kotlin:1.12.0")

    // Activity KTX for viewModels()
    implementation ("androidx.activity:activity-ktx:1.3.0")

    // Room
    implementation ("androidx.room:room-runtime:2.3.0")
    kapt ("androidx.room:room-compiler:2.3.0")
    implementation ("androidx.room:room-ktx:2.3.0")
    implementation ("androidx.room:room-paging:2.4.0-alpha04")

    // Preferences DataStore
    implementation ("androidx.datastore:datastore-preferences:1.0.0-rc02")

    //JSoup
    implementation ("org.jsoup:jsoup:1.14.1")

    //Google Play Core
    implementation ("com.google.android.play:core:1.10.0")

    //Firebase
    implementation (platform ("com.google.firebase:firebase-bom:26.2.0"))
    implementation ("com.google.firebase:firebase-messaging-ktx:22.0.0")
    implementation ("com.google.firebase:firebase-crashlytics-ktx:18.2.0")
    implementation ("com.google.firebase:firebase-analytics-ktx:19.0.0")

    //Ads
//    implementation "com.google.android.gms:play-services-ads:20.2.0"

    //WebKit
    implementation ("androidx.webkit:webkit:1.4.0")

    //Preference
    implementation ("androidx.preference:preference-ktx:1.1.1")

    //Paging Library
    implementation ("androidx.paging:paging-runtime-ktx:3.0.1")

    //Epoxy
    implementation ("com.airbnb.android:epoxy:4.6.2")
    annotationProcessor ("com.airbnb.android:epoxy-processor:4.6.2")

    //Stetho
    implementation ("com.facebook.stetho:stetho:1.6.0")

    //Chucker
    debugImplementation ("com.github.chuckerteam.chucker:library:3.5.0")
    releaseImplementation ("com.github.chuckerteam.chucker:library-no-op:3.5.0")

    //Iconic
    implementation ("com.mikepenz:iconics-core:5.3.0")

    //Test
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
}