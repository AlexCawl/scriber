package org.alexcawl.scriber.cv

import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.FrameRecorder

data class VideoHolder(
    val video: Video
) {
    val grabber: FrameGrabber = video.grabber
    val recorder: FrameRecorder = video.recorder.apply {

    }
}