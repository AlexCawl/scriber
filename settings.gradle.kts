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

rootProject.name = "scriber"

include(":app")

// :core
include(":core:common")
include(":core:ui")
include(":core:mvi")
include(":core:cv")
include(":core:data")

// :video
include(":video:ui")
include(":video:domain")
include(":video:data")

// :configuration
include(":configuration")
include(":configuration:ui")
include(":configuration:domain")
