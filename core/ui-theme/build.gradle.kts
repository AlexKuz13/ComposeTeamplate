plugins {
    id(Libs.plugin.library)
}

common()
compose()

dependencies {
    implementation(projects.coreCommon)

    implementation(*Libs.bundle.splashscreen)
    implementation(*Libs.bundle.composeTheme)
}