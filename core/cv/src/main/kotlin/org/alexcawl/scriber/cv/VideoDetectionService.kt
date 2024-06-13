package org.alexcawl.scriber.cv

import org.bytedeco.javacv.FFmpegFrameGrabber
import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.FrameRecorder
import org.bytedeco.javacv.Java2DFrameUtils.toBufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO

class VideoDetectionService {
    fun getMotionDetectedVideo(originalVideo: File): Sequence<ByteArray> =
        FFmpegFrameGrabber(originalVideo).use { grabber: FrameGrabber ->
            val frames: Sequence<Frame> = downloadVideo(grabber)
            detectMotion(frames).map {
                val stream = ByteArrayOutputStream()
                ImageIO.write(toBufferedImage(it), "jpg", stream)
                stream.toByteArray()
            }
        }

    fun downloadMotionDetectedVideo(originalVideo: File, detectedVideo: File) =
        FFmpegFrameGrabber(originalVideo).use { grabber: FrameGrabber ->
            val frames: Sequence<Frame> = downloadVideo(grabber)
            produceEqualRecorder(detectedVideo, grabber).use { recorder: FrameRecorder ->
                uploadVideo(recorder, detectMotion(frames))
            }
        }
}