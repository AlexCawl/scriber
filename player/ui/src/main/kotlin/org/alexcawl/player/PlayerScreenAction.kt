package org.alexcawl.player

sealed interface PlayerScreenAction {
    data object Toggle : PlayerScreenAction

    data object Restart : PlayerScreenAction
}