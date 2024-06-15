package org.alexcawl.scriber.cv.manager

import org.alexcawl.common.DetectionParameters
import org.alexcawl.scriber.cv.OpenCvDetector
import org.alexcawl.scriber.cv.utils.downloadVideo
import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.Java2DFrameUtils.toBufferedImage
import org.bytedeco.javacv.OpenCVFrameGrabber
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

class CameraDetectionManager(private val detectionParameters: DetectionParameters) {
    fun getMotionDetectedVideo(): Sequence<ByteArray> {
        val detector = OpenCvDetector(detectionParameters)
        return OpenCVFrameGrabber(0).use { grabber: FrameGrabber ->
            detector.detect(downloadVideo(grabber)).map {
                val stream = ByteArrayOutputStream()
                ImageIO.write(toBufferedImage(it), "jpg", stream)
                stream.toByteArray()
            }
        }
    }
}