plugins {
    id("dagger-convention")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:mvi"))

    implementation(project(":video:ui"))
    implementation(project(":video:domain"))
    implementation(project(":video:data"))
}