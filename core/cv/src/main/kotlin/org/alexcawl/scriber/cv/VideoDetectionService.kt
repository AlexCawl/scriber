package org.alexcawl.scriber.cv

import org.bytedeco.javacv.FFmpegFrameGrabber
import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.FrameRecorder
import org.bytedeco.javacv.Java2DFrameUtils.toBufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO

// TODO: move into :cv module
class VideoDetectionService {
    @Deprecated("unused")
    fun rewriteVideo(from: File, to: File): Result<Unit> = runCatching {
        FFmpegFrameGrabber(from).use { grabber: FrameGrabber ->
            val frames: Sequence<Frame> = downloadVideo(grabber).getOrThrow()
            produceEqualRecorder(to, grabber).use { recorder: FrameRecorder ->
                uploadVideo(recorder, frames)
            }
        }
    }

    fun getMotionDetectedVideo(originalVideo: File): Result<Sequence<ByteArray>> = runCatching {
        FFmpegFrameGrabber(originalVideo).use { grabber: FrameGrabber ->
            downloadVideo(grabber).mapCatching { detectMotion(it).getOrThrow() }.getOrThrow().map {
                val stream = ByteArrayOutputStream()
                ImageIO.write(toBufferedImage(it), "jpg", stream)
                stream.toByteArray()
            }
        }
    }

    fun downloadMotionDetectedVideo(originalVideo: File, detectedVideo: File): Result<Unit> = runCatching {
        FFmpegFrameGrabber(originalVideo).use { grabber: FrameGrabber ->
            val frames: Sequence<Frame> = downloadVideo(grabber).mapCatching { detectMotion(it).getOrThrow() }.getOrThrow()
            produceEqualRecorder(detectedVideo, grabber).use { recorder: FrameRecorder ->
                uploadVideo(recorder, frames)
            }
        }
    }
}