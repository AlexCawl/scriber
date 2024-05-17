plugins {
    id("compose-convention")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:mvi"))
    implementation(project(":video:domain"))
}