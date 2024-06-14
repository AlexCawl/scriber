package org.alexcawl.scriber.cv

import org.alexcawl.common.DetectionParameters
import org.alexcawl.scriber.cv.utils.downloadVideo
import org.alexcawl.scriber.cv.utils.produceEqualRecorder
import org.alexcawl.scriber.cv.utils.uploadVideo
import org.bytedeco.javacv.FFmpegFrameGrabber
import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.FrameRecorder
import org.bytedeco.javacv.Java2DFrameUtils.toBufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO

class VideoDetectionManager {
    fun getMotionDetectedVideo(originalVideo: File, params: DetectionParameters): Sequence<ByteArray> {
        val holder = VideoDetector(params, originalVideo)
        return FFmpegFrameGrabber(originalVideo).use { grabber: FrameGrabber ->
            val frames: Sequence<Frame> = downloadVideo(grabber)
            holder.detect(frames).map {
                val stream = ByteArrayOutputStream()
                ImageIO.write(toBufferedImage(it), "jpg", stream)
                stream.toByteArray()
            }
        }
    }

    fun loadMotionDetectedVideo(originalVideo: File, detectedVideo: File, params: DetectionParameters) {
        val holder = VideoDetector(params, originalVideo)
        FFmpegFrameGrabber(originalVideo).use { grabber: FrameGrabber ->
            val frames: Sequence<Frame> = downloadVideo(grabber)
            produceEqualRecorder(detectedVideo, grabber).use { recorder: FrameRecorder ->
                uploadVideo(recorder, holder.detect(frames))
            }
        }
    }
}