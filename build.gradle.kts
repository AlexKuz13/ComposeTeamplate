buildscript {

    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath(Libs.classpath.android_gradle)
        classpath(Libs.classpath.kotlin_gradle)
        classpath(Libs.classpath.kotlin_serialization)
        classpath(Libs.classpath.hilt_gradle)
        classpath(Libs.classpath.google_services)
        classpath(Libs.classpath.navigation_safeArgs_gradle)
        classpath(Libs.classpath.kotlinter)
    }
}

plugins {
    id(Libs.plugin.sonarqube) version (Libs.sonarqubePluginVersion)
}

project.tasks.getByName("sonarqube").doFirst {
    sonarqube {
        properties {
            property("sonar.projectKey", SonarQubeParams.ProjectKey)
            property("sonar.projectName", SonarQubeParams.ProjectName)
            property("sonar.language", SonarQubeParams.Language)
            property("sonar.sources", SonarQubeParams.Sources)
            property("sonar.host.url", SonarQubeParams.HostUrl)
            property("sonar.login", SonarQubeParams.Login)
        }
    }
}