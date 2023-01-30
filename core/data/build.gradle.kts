plugins {
    id(Libs.plugin.library)
}

common()
hilt()
room()

android {
    buildTypes {
        debug {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"debug_url\""
            )
        }
        release {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"release_url\""
            )
        }
    }
}

dependencies {
    implementation(projects.coreCommon)
    
    implementation(*Libs.bundle.datastorePreferences)

    api(*Libs.bundle.requests)
    api(*Libs.bundle.kotlinSerialization)
}