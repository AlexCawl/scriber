plugins {
    id("kotlin-convention")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:cv"))
    implementation(project(":core:data"))

    implementation(project(":camera:data"))
}