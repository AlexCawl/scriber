package org.alexcawl.scriber.cv

import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.OpenCVFrameConverter
import org.bytedeco.opencv.global.opencv_core.CV_8UC1
import org.bytedeco.opencv.global.opencv_core.subtract
import org.bytedeco.opencv.global.opencv_imgproc.*
import org.bytedeco.opencv.opencv_core.*

private val converter: OpenCVFrameConverter.ToMat = OpenCVFrameConverter.ToMat()

fun detectMotion(frames: Sequence<Frame>): Result<Sequence<Frame>> = runCatching {
    sequence {
        var detectedImage: Mat?
        var outerBox: Mat
        var differenceFrame: Mat? = null
        var bwFrame: Mat? = null
        var isFirstFrame = true

        frames.map(converter::convert).forEach { currentFrame ->
            detectedImage = currentFrame.clone()
            outerBox = Mat(currentFrame.size(), CV_8UC1)
            cvtColor(currentFrame, outerBox, COLOR_BGR2GRAY)
            GaussianBlur(outerBox, outerBox, Size(3, 3), 0.0)

            when (isFirstFrame) {
                true -> {
                    bwFrame = Mat(outerBox.size(), CV_8UC1)
                    differenceFrame = outerBox.clone()
                    isFirstFrame = false
                }

                false -> {
                    subtract(outerBox, bwFrame, differenceFrame)
                    adaptiveThreshold(
                        differenceFrame, differenceFrame, 255.0,
                        ADAPTIVE_THRESH_MEAN_C,
                        THRESH_BINARY_INV, 5, 2.0
                    )
                    detectionContours(differenceFrame!!, detectedImage!!).forEach {
                        val scalar = Scalar(0.0, 255.0, 0.0, 0.0)
                        rectangle(detectedImage, it.br(), it.tl(), scalar)
                    }
                }
            }
            bwFrame = outerBox.clone()
            yield(converter.convert(detectedImage!!))
        }
    }
}

private fun detectionContours(frame: Mat, destination: Mat): List<Rect> {
    val v: Mat = Mat()
    val vv: Mat = frame.clone()
    val contours = MatVector()
    findContours(vv, contours, v, RETR_LIST, CHAIN_APPROX_SIMPLE)

    val maxArea = 100.0
    var rectangle: Rect
    val rectangleList: MutableList<Rect> = mutableListOf()

    for (index in 0 until contours.size()) {
        val contour = contours[index]
        val area = contourArea(contour)
        if (area > maxArea) {
            rectangle = boundingRect(contours[index])
            rectangleList.add(rectangle)
            drawContours(destination, contours, index.toInt(), Scalar(0.0, 0.0, 255.0, 0.0))
        }
    }
    v.release()
    vv.release()
    return rectangleList
}