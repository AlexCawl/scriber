package org.alexcawl.scriber

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.alexcawl.scriber.di.ApplicationComponentStore
import org.alexcawl.scriber.di.DaggerApplicationComponent
import org.alexcawl.scriber.mvi.compose.ComponentScope
import org.alexcawl.scriber.ui.ApplicationScreen
import org.alexcawl.scriber.video.di.VideoComponentStore

private inline fun <R> loadDagger(block: () -> R): R = run {
    val applicationComponent = DaggerApplicationComponent.create()
    ApplicationComponentStore.component = applicationComponent
    VideoComponentStore.dependencies = applicationComponent
    block.invoke()
}

fun main() = loadDagger {
    application {
        val logo = painterResource("scriber_logo_colored.svg")
        ComponentScope(ApplicationComponentStore.component) {
            Window(onCloseRequest = ::exitApplication, icon = logo) {
                ApplicationScreen()
            }
        }
    }
}
