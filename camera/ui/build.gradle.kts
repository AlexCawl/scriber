plugins {
    id("compose-convention")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:cv"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":core:mvi"))

    implementation(project(":camera:data"))
    implementation(project(":camera:domain"))

    implementation(project(":configuration:ui"))
}