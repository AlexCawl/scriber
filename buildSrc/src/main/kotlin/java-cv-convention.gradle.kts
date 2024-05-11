import org.gradle.kotlin.dsl.the

// Workaround: https://github.com/gradle/gradle/issues/15383
val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

plugins {
    `java-gradle-plugin`
}

java {
    version = libs.versions.jdk.get()
}

dependencies {
    implementation(libs.opencv)
}