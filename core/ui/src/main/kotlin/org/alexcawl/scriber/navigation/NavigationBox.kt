package org.alexcawl.scriber.navigation

import androidx.compose.runtime.Composable


class Navigation(
    val navController: NavigationController,
    val contents: @Composable Builder.() -> Unit
) {
    @Composable
    fun build() {
        Builder().renderContents()
    }

    inner class Builder internal constructor(val navController: NavigationController = this@Navigation.navController) {
        @Composable
        fun renderContents() {
            this@Navigation.contents(this)
        }
    }
}


@Composable
fun Navigation.Builder.destination(route: String, content: @Composable () -> Unit) =
    when (navController.currentDestination.value) {
        route -> content()
        else -> Unit
    }
