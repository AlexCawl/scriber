package org.alexcawl.scriber.camera.camera

sealed interface CameraScreenAction {
    data object Toggle : CameraScreenAction
}