plugins {
    id(Libs.plugin.application)
    id(Libs.plugin.triplet_play) version (Libs.tripletPlayPluginVersion)
}

common()
compose()
dagger()
lifecycle()
paging()

android {
    defaultConfig {
        versionName = App.name
        versionCode = App.code
        applicationId = App.packageName
        setProperty("archivesBaseName", App.name)
    }

    signingConfigs {
        create(BuildTypes.release) {
            storeFile = rootProject.file(KeystoreParamsRelease.path)
            storePassword = KeystoreParamsRelease.storePassword
            keyAlias = KeystoreParamsRelease.keyAlias
            keyPassword = KeystoreParamsRelease.keyPassword
        }
        getByName(BuildTypes.debug) {
            storeFile = rootProject.file(KeystoreParamsDebug.path)
            storePassword = KeystoreParamsDebug.storePassword
            keyAlias = KeystoreParamsDebug.keyAlias
            keyPassword = KeystoreParamsDebug.keyPassword
        }
    }

    buildTypes {
        getByName(BuildTypes.release) {
            versionNameSuffix = BuildTypes.releaseSuffix
            signingConfig = signingConfigs.getByName(BuildTypes.release)
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName(BuildTypes.debug) {
            versionNameSuffix = BuildTypes.debugSuffix
            applicationIdSuffix = BuildTypes.debug
            signingConfig = signingConfigs.getByName(BuildTypes.debug)
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(*Libs.bundle.composeTheme)
}

play {
    track.set("internal")
    releaseName.set(App.name.capitalize())
}