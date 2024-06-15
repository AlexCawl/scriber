package org.alexcawl.scriber.camera.camera

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import org.alexcawl.scriber.mvi.core.Store
import javax.inject.Inject

class CameraScreenStore @Inject constructor(
    scope: CoroutineScope
) : Store<CameraScreenState, CameraScreenAction>(scope, CameraScreenState.Initial) {
    private val playerOpenedState = MutableStateFlow(false)

    init {
        task {
            playerOpenedState.collect { isOpened ->
                reduce {
                    CameraScreenState.State(isOpened)
                }
            }
        }
    }

    override fun handle(event: CameraScreenAction) = when (event) {
        CameraScreenAction.ToggleCameraPlayer -> task {
            val playerOpened: Boolean = playerOpenedState.first()
            playerOpenedState.emit(playerOpened.not())
        }
    }
}