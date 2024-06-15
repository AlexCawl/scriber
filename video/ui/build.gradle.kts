plugins {
    id("compose-convention")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:cv"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":core:mvi"))

    implementation(project(":video:data"))
    implementation(project(":video:domain"))

    implementation(project(":configuration:ui"))
}