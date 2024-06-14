package org.alexcawl.player

import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Store

class PlayerScreenStore(
    scope: CoroutineScope
) : Store<PlayerScreenState, PlayerScreenAction>(scope, PlayerScreenState.Initial) {
    override fun handle(event: PlayerScreenAction) {
        when (event) {
            PlayerScreenAction.Toggle -> task {

            }
            PlayerScreenAction.Restart -> task {

            }
        }
    }
}