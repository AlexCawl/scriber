package org.alexcawl.scriber.cv

import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.FrameRecorder
import java.io.File

sealed interface VideoHolder {
    data object Empty : VideoHolder

    data class Filled(
        val video: File,
        val grabber: FrameGrabber,
        val recorder: FrameRecorder
    ) : VideoHolder
}