@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven("https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "kmp-desktop-template"

include(":app")

// :core
include(":core:common")
include(":core:data")
include(":core:di")
include(":core:ui")

// :cv
include(":cv")

// :video
include(":video")
include(":video:ui")
include(":video:domain")
include(":video:data")
