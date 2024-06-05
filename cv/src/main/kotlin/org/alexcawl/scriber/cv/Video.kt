package org.alexcawl.scriber.cv

import org.bytedeco.javacv.FFmpegFrameGrabber
import org.bytedeco.javacv.FFmpegFrameRecorder
import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.FrameRecorder
import java.io.File

@JvmInline
value class Video(val path: File) {
    internal val grabber: FrameGrabber
        get() = FFmpegFrameGrabber(path)

    internal val recorder: FrameRecorder
        get() = FFmpegFrameRecorder(path, 0)
}
