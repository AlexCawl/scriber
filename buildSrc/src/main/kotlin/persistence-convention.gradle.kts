// Workaround: https://github.com/gradle/gradle/issues/15383
val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

plugins {
    id("kotlin-convention")
    id("app.cash.sqldelight")
}

dependencies {
    implementation(libs.kotlin.sql.driver)
}