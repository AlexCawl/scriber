package org.alexcawl.scriber.video

import org.alexcawl.scriber.cv.downloadVideo
import org.alexcawl.scriber.cv.produceEqualRecorder
import org.alexcawl.scriber.cv.uploadVideo
import org.bytedeco.javacv.FFmpegFrameGrabber
import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.FrameRecorder
import java.io.File

class VideoAnalyzer {
    fun rewriteVideo(from: File, to: File): Result<Unit> = runCatching {
        FFmpegFrameGrabber(from).use { grabber: FFmpegFrameGrabber ->
            val frames: Sequence<Frame> = downloadVideo(grabber).getOrThrow()
            produceEqualRecorder(to, grabber).use { recorder: FrameRecorder ->
                uploadVideo(recorder, frames)
            }
        }
    }
}