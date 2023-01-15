plugins {
    id(Libs.plugin.library)
}

common()
hilt()
room()

dependencies {
    implementation(projects.coreCommon)

    api(*Libs.bundle.requests)
    api(*Libs.bundle.kotlinSerialization)
}