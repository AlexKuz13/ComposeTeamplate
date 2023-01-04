object Libs {

    private const val gmsVersion = "4.3.4"
    private const val gradleVersion = "7.0.4"
    private const val desugarVersion = "1.1.5"
    private const val kotlinVersion = "1.7.10"
    private const val kotlinSerializationVersion = "1.3.0"
    private const val coroutinesVersion = "1.6.0"
    private const val kotlinterVersion = "3.8.0"

    const val composeVersion = "1.3.1"
    private const val composeMaterialVersion = "1.0.0-alpha09"
    private const val splashVersion = "1.0.0"
    private const val activityVersion = "1.4.0"
    private const val materialVersion = "1.5.0"
    private const val pagingComposeVersion = "1.0.0-alpha17"
    private const val accompanistVersion = "0.24.6-alpha"
    private const val coilVersion = "2.1.0"

    private const val lifecycleVersion = "2.4.0"
    private const val navigationVersion = "2.5.0"

    private const val hiltVersion = "2.44.2"
    private const val hiltJetpackVersion = "1.0.0"

    private const val datastoreVersion = "1.0.0"
    private const val securityVersion = "1.1.0-alpha03"
    private const val preferenceVersion = "1.1.1"
    private const val roomVersion = "2.4.0"
    private const val jsonConverterVersion = "0.8.0"
    private const val retrofitVersion = "2.9.0"
    private const val okHttpVersion = "4.9.0"
    private const val chuckVersion = "3.5.2"

    private const val mockitoVersion = "2.7.22"
    private const val truthVersion = "0.34"
    private const val junitVersion = "4.12"
    private const val espressoVersion = "3.1.0"
    private const val runnerVersion = "1.1.0"

    private const val googleServicesAuthVersion = "20.2.0"

    private const val protobufVersion = "3.18.0"
    const val protobufPluginVersion = "0.8.16"
    private const val protocVersion = "3.10.0"

    const val sonarqubePluginVersion = "3.4.0.2513"
    const val tripletPlayPluginVersion = "3.7.0"

    private const val workManagerVersion = "2.7.1"
    private const val hiltWorkManagerVersion = "1.0.0"

    private const val jetpackVersion = "2.5.1"

    private const val permissionVersion = "0.20.0"

    private const val imageCropperVersion = "4.3.2"
    private const val compressorVersion = "3.0.1"

    object plugin {
        const val application = "com.android.application"
        const val library = "com.android.library"
        const val kotlin_kapt = "kotlin-kapt"
        const val kotlin_parcelize = "kotlin-parcelize"
        const val kotlin_serialization = "kotlinx-serialization"
        const val kotlin_android = "kotlin-android"
        const val hilt = "dagger.hilt.android.plugin"
        const val lint = "org.jmailen.kotlinter"
        const val google_services = "com.google.gms.google-services"
        const val protobuf = "com.google.protobuf"
        const val sonarqube = "org.sonarqube"
        const val triplet_play = "com.github.triplet.play"
    }

    object classpath {
        const val google_services = "com.google.gms:google-services:$gmsVersion"
        const val android_gradle = "com.android.tools.build:gradle:$gradleVersion"
        const val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val kotlin_serialization = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
        const val hilt_gradle = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
        const val navigation_safeArgs_gradle =
            "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
        const val kotlinter = "org.jmailen.gradle:kotlinter-gradle:$kotlinterVersion"
    }

    object bundle {
        val kotlin = arrayOf("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
        val coroutines = arrayOf(
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion",
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        )
        val kotlinSerialization = arrayOf(
            "org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinSerializationVersion",
            "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion"
        )

        val desugar = arrayOf("com.android.tools:desugar_jdk_libs:$desugarVersion")

        val hilt = arrayOf(
            "com.google.dagger:hilt-android:$hiltVersion",
            "androidx.hilt:hilt-navigation-compose:$hiltJetpackVersion"
        )
        const val hiltKapt = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        const val hiltKaptViewModel = "androidx.hilt:hilt-compiler:$hiltJetpackVersion"

        val firebase = arrayOf<String>(/*
            "com.google.firebase:firebase-crashlytics-ktx:$crashlyticsVersion",
            "com.google.firebase:firebase-analytics-ktx:$analyticsVersion",
            "com.google.firebase:firebase-messaging-ktx:$messagingVersion",
            "com.google.firebase:firebase-config-ktx:$configVersion"*/
        )

        val composeCompiler = arrayOf("androidx.compose.compiler:compiler:$composeVersion")
        val composeRuntime = arrayOf("androidx.compose.runtime:runtime:$composeVersion")
        val composeTheme = arrayOf(
            "androidx.compose.ui:ui:$composeVersion",
            "androidx.compose.ui:ui-tooling:$composeVersion",
            "androidx.compose.ui:ui-tooling-preview:$composeVersion",
            "androidx.compose.material:material:$composeVersion",
            "androidx.compose.material3:material3:$composeMaterialVersion",
            "androidx.compose.material:material-icons-extended:$composeVersion",
            "com.google.android.material:material:$materialVersion"
        )
        val composeKit = arrayOf(
            "androidx.activity:activity-compose:$activityVersion",
            "androidx.compose.foundation:foundation:$composeVersion",
            "androidx.compose.foundation:foundation-layout:$composeVersion",
            "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion",
            "com.google.accompanist:accompanist-pager:$accompanistVersion",
            "com.google.accompanist:accompanist-placeholder-material:$accompanistVersion",
            "com.google.accompanist:accompanist-swiperefresh:$accompanistVersion",
            "com.google.accompanist:accompanist-flowlayout:$accompanistVersion",
            "androidx.compose.animation:animation:$composeVersion",
            "io.coil-kt:coil-compose:$coilVersion",
            "com.google.accompanist:accompanist-permissions:$permissionVersion"
        )

        val splashscreen = arrayOf("androidx.core:core-splashscreen:$splashVersion")
        val paging = arrayOf("androidx.paging:paging-compose:$pagingComposeVersion")

        val lifecycle = arrayOf(
            "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion",
            "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion",
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        )
        const val lifecycleKapt = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"

        val navigation = arrayOf(
            "androidx.navigation:navigation-compose:$navigationVersion"
        )

        val datastorePreferences =
            arrayOf("androidx.datastore:datastore-preferences:$datastoreVersion")
        const val datastore = "androidx.datastore:datastore:1.0.0"
        val cryptoPreference = arrayOf("androidx.security:security-crypto:$securityVersion")

        val room = arrayOf(
            "androidx.room:room-runtime:$roomVersion",
            "androidx.room:room-ktx:$roomVersion"
        )
        const val roomKapt = "androidx.room:room-compiler:$roomVersion"

        val requests = arrayOf(
            "com.squareup.retrofit2:retrofit:$retrofitVersion",
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$jsonConverterVersion",
            "com.squareup.okhttp3:okhttp:$okHttpVersion",
            "com.squareup.okhttp3:logging-interceptor:$okHttpVersion",
            "com.github.chuckerteam.chucker:library:$chuckVersion"
        )

        const val unitTestsRunner = "android.support.test.runner.AndroidJUnitRunner"
        val unitTests = arrayOf(
            "junit:junit:$junitVersion",
            "org.mockito:mockito-core:$mockitoVersion",
            "org.mockito:mockito-inline:$mockitoVersion",
            "org.mockito:mockito-android:$mockitoVersion",
            "com.google.truth:truth:$truthVersion"
        )
        val uiTests = arrayOf(
            "androidx.test.espresso:espresso-core:$espressoVersion",
            "androidx.test.espresso:espresso-intents:$espressoVersion",
            "androidx.test:runner:$runnerVersion",
            "org.mockito:mockito-inline:$mockitoVersion",
            "org.mockito:mockito-android:$mockitoVersion",
            "com.google.truth:truth:$truthVersion"
        )

        const val googleServicesAuth =
            "com.google.android.gms:play-services-auth:$googleServicesAuthVersion"

        const val protobuf = "com.google.protobuf:protobuf-javalite:$protobufVersion"
        const val protoc = "com.google.protobuf:protoc:$protocVersion"

        const val workManager = "androidx.work:work-runtime-ktx:$workManagerVersion"
        const val hiltWorkManager = "androidx.hilt:hilt-work:$hiltWorkManagerVersion"
        const val hiltWorkManagerKapt = "androidx.hilt:hilt-compiler:$hiltWorkManagerVersion"

        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$jetpackVersion"

        const val imageCropper = "com.github.CanHub:Android-Image-Cropper:$imageCropperVersion"
        const val compressor = "id.zelory:compressor:$compressorVersion"
    }
}