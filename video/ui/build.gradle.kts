plugins {
    id("compose-convention")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:mvi"))
    implementation(project(":video:domain"))
    implementation(project(":configuration:ui"))
    implementation(project(":player:ui"))
}