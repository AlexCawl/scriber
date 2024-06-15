import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("kotlin-convention")
    id("compose-convention")
    id("opencv-convention")
    id("persistence-convention")
}

dependencies {
    implementation(compose.desktop.currentOs)

    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:mvi"))
    implementation(project(":core:cv"))
    implementation(project(":core:data"))

    implementation(project(":configuration"))
    implementation(project(":configuration:ui"))
    implementation(project(":configuration:domain"))

    implementation(project(":video:ui"))
    implementation(project(":video:domain"))
    implementation(project(":video:data"))

    implementation(project(":camera:ui"))
    implementation(project(":camera:domain"))
    implementation(project(":camera:data"))
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            outputBaseDir.set(project.buildDir.resolve("package"))
            packageName = "scriber"
            version = "1.0.0"
            description = "Detect motion in videos"
            copyright = "© 2024 Mikhail Babushkin. All rights reserved."
            vendor = "Mikhail Babushkin"
        }
    }
}
