// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google() // Google's Maven repository
        mavenCentral()  // Maven Central
        // jcenter() // Deprecated, remove unless necessary
    }

    dependencies {
        // Use the latest compatible AGP version for your project
        classpath("com.android.tools.build:gradle:8.1.0")

        // Firebase services plugin
        classpath("com.google.gms:google-services:4.4.2")
    }
}

allprojects {
    repositories {

        // jcenter() // Deprecated, remove unless necessary
    }
}
