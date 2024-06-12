plugins {
    id("kotlin-convention")
    id("compose-convention")
    id("dagger-convention")
    id("opencv-convention")
    id("persistence-convention")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:mvi"))
    implementation(project(":core:cv"))

    implementation(project(":configuration:ui"))
    implementation(project(":configuration:domain"))
    implementation(project(":configuration:data"))
}