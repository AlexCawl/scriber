import gradle.kotlin.dsl.accessors._35643e33f7d1312effc9c7ed341a53e0.implementation

// Workaround: https://github.com/gradle/gradle/issues/15383
val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

plugins {
    id("kotlin-convention")
    id("app.cash.sqldelight")
}

dependencies {
    implementation(libs.kotlin.sql.driver)
    implementation(libs.datastore)
}