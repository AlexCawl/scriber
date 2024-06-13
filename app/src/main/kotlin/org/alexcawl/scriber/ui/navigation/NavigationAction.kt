package org.alexcawl.scriber.ui.navigation

sealed interface NavigationAction {
    data object NavigateToLoading : NavigationAction

    data object NavigateToVideo : NavigationAction

    data object NavigateToCamera : NavigationAction
}