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

class VideoDetectionManager(
    private val videoFile: File,
    private val detectionParameters: DetectionParameters
) {
    fun getMotionDetectedVideo(): Sequence<ByteArray> {
        val holder = VideoDetector(detectionParameters, videoFile)
        return FFmpegFrameGrabber(videoFile).use { grabber: FrameGrabber ->
            val frames: Sequence<Frame> = downloadVideo(grabber)
            holder.detect(frames).map {
                val stream = ByteArrayOutputStream()
                ImageIO.write(toBufferedImage(it), "jpg", stream)
                stream.toByteArray()
            }
        }
    }

    fun loadMotionDetectedVideo(outputVideoFile: File = videoFile.getOutput()) {
        val holder = VideoDetector(detectionParameters, videoFile)
        FFmpegFrameGrabber(videoFile).use { grabber: FrameGrabber ->
            val frames: Sequence<Frame> = downloadVideo(grabber)
            produceEqualRecorder(outputVideoFile, grabber).use { recorder: FrameRecorder ->
                uploadVideo(recorder, holder.detect(frames))
            }
        }
    }

    private fun File.getOutput(): File {
        val directory: String = this.parent
        val name: String = this.nameWithoutExtension + "log"
        val extension: String = this.extension
        return File(directory, "$name.$extension")
    }
}