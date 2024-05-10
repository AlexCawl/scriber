import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("kotlin-convention")
    id("compose-convention")
}

group = "org.alexcawl.template"
version = "1.0-SNAPSHOT"

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation(project(":core:common"))
    implementation(project(":core:di"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            outputBaseDir.set(project.buildDir.resolve("package"))
            packageName = "kmp-desktop-template"
            packageVersion = "1.0.0"
        }
    }
}
