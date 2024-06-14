package org.alexcawl.player

sealed interface PlayerScreenAction {
    data object Restart : PlayerScreenAction
}