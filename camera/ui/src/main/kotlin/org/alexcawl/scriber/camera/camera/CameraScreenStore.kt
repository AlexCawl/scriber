package org.alexcawl.scriber.camera.camera

import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Store
import javax.inject.Inject

class CameraScreenStore @Inject constructor(
    scope: CoroutineScope
) : Store<CameraScreenState, CameraScreenAction>(scope, CameraScreenState.Initial) {
    override fun handle(event: CameraScreenAction) {
        TODO("Not yet implemented")
    }
}