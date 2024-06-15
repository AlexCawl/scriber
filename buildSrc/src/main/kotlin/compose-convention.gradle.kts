// Workaround: https://github.com/gradle/gradle/issues/15383
val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

plugins {
    id("kotlin-convention")
    id("org.jetbrains.compose")
}

dependencies {
    implementation(compose.desktop.common)
    implementation(libs.kotlin.coroutines.swing)
    implementation(libs.material.icons)
}