plugins {
    id("kotlin-convention")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:mvi"))
    implementation(project(":core:cv"))
    implementation(project(":core:data"))

    implementation(project(":video:ui"))
    implementation(project(":video:domain"))
    implementation(project(":video:data"))

    implementation(project(":configuration:ui"))
    implementation(project(":configuration:domain"))
}