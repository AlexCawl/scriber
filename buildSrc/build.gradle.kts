@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
}

dependencies {
    // Workaround: https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    // Plugins as dependencies
    implementation(libs.pluginDependency.kotlin.gradle) // -> id("org.jetbrains.kotlin.jvm")
    implementation(libs.pluginDependency.kotlin.serialization) // -> id("org.jetbrains.kotlin.plugin.serialization")
    implementation(libs.pluginDependency.kotlin.ksp) // -> id("com.google.devtools.ksp")
    implementation(libs.pluginDependency.kotlin.compose) // -> id("org.jetbrains.compose")
    implementation(libs.pluginDependency.kotlin.sql) // -> id("app.cash.sqldelight")
}

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
    }
}