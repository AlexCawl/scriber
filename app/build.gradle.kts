import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("kotlin-convention")
    id("compose-convention")
    id("opencv-convention")
    id("persistence-convention")
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:mvi"))
    implementation(project(":core:cv"))
    implementation(project(":core:data"))
    implementation(project(":configuration"))
    implementation(project(":configuration:ui"))
    implementation(project(":configuration:domain"))
    implementation(project(":configuration:data"))
    implementation(project(":video"))
    implementation(project(":video:ui"))
    implementation(project(":video:domain"))
    implementation(project(":video:data"))
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
