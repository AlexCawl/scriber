package org.alexcawl.scriber.cv.manager

import org.alexcawl.common.DetectionParameters
import org.alexcawl.scriber.cv.OpenCvDetector
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
        val detector = OpenCvDetector(detectionParameters)
        return FFmpegFrameGrabber(videoFile).use { grabber: FrameGrabber ->
            val frames: Sequence<Frame> = downloadVideo(grabber)
            detector.detect(frames).map {
                val stream = ByteArrayOutputStream()
                ImageIO.write(toBufferedImage(it), "jpg", stream)
                stream.toByteArray()
            }
        }
    }

    fun loadMotionDetectedVideo(suffix: String) {
        val detector = OpenCvDetector(detectionParameters)
        FFmpegFrameGrabber(videoFile).use { grabber: FrameGrabber ->
            val frames: Sequence<Frame> = downloadVideo(grabber)
            val outputVideoFile: File = videoFile.getOutput(suffix)
            produceEqualRecorder(outputVideoFile, grabber).use { recorder: FrameRecorder ->
                uploadVideo(recorder, detector.detect(frames))
            }
        }
    }

    private fun File.getOutput(suffix: String = "_log"): File {
        val directory: String = this.parent
        val name: String = this.nameWithoutExtension + suffix
        val extension: String = this.extension
        return File(directory, "$name.$extension")
    }
}