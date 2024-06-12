package org.alexcawl.scriber.ui

sealed interface ApplicationAction {
    data object ToggleTheme : ApplicationAction

    data object NavigateToCamera : ApplicationAction

    data object NavigateToVideo : ApplicationAction
}