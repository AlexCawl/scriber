package org.alexcawl.scriber

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.alexcawl.scriber.mvi.compose.ComponentScope
import org.alexcawl.scriber.ui.ApplicationScreen

fun main() = run {
    val applicationComponent = DaggerApplicationComponent.create()
    application {
        val logo = painterResource("scriber_logo_colored.svg")
        ComponentScope(applicationComponent) {
            Window(onCloseRequest = ::exitApplication, icon = logo) {
                ApplicationScreen()
            }
        }
    }
}
