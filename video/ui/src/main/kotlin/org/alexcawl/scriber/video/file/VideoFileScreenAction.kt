package org.alexcawl.scriber.video.file

import java.io.File

sealed interface VideoFileScreenAction {
    data object UnselectVideoFile : VideoFileScreenAction

    data class SelectVideoFile(val file: File) : VideoFileScreenAction

    data class SetSuffix(val fileSuffix: String) : VideoFileScreenAction
}