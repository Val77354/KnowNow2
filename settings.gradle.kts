pluginManagement {
    repositories {
        // Google's Maven repository for Android and Firebase-related plugins
        google {
            content {
                includeGroupByRegex("com\\.android.*") // Android Gradle Plugin
                includeGroupByRegex("com\\.google.*") // Firebase, Play Services, etc.
                includeGroupByRegex("androidx.*")     // AndroidX libraries
            }
        }
        mavenCentral() // Maven Central repository
        gradlePluginPortal() // Gradle Plugin Portal for third-party plugins
    }
    plugins {
        // Google Services plugin for Firebase integration
        id("com.google.gms.google-services") version "4.4.2"
        // Android Gradle Plugin (used in app-level build files)
        id("com.android.application") version "8.1.1"
        // Kotlin plugin for Android development
        id("org.jetbrains.kotlin.android") version "1.9.10"
    }
}

dependencyResolutionManagement {
    // Fail if project-level repositories are declared in build.gradle.kts
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google() // Google's Maven repository
        mavenCentral() // Maven Central repository
    }
}

rootProject.name = "KnowNow"
include(":app")
