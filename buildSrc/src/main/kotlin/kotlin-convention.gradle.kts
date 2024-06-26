@file:Suppress("UnstableApiUsage")

// Workaround: https://github.com/gradle/gradle/issues/15383
val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.devtools.ksp")
}

kotlin {
    val javaVersion = JavaLanguageVersion.of(libs.versions.jdk.get());
    jvmToolchain(javaVersion.asInt())
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.serialization.json)
    testImplementation(libs.junit)
    testRuntimeOnly(libs.junit.engine)
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}

tasks.test {
    useJUnitPlatform()
}