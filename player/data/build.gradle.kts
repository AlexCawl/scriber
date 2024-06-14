plugins {
    id("persistence-convention")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:cv"))

    implementation(project(":configuration:data"))
    implementation(project(":video:data"))
}