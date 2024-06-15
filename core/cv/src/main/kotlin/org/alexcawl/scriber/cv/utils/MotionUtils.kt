package org.alexcawl.scriber.cv.utils

import org.alexcawl.common.DetectionParameters
import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.OpenCVFrameConverter
import org.bytedeco.opencv.global.opencv_core.CV_8UC1
import org.bytedeco.opencv.global.opencv_core.subtract
import org.bytedeco.opencv.global.opencv_imgproc.*
import org.bytedeco.opencv.opencv_core.*
import kotlin.properties.Delegates

private val converter: OpenCVFrameConverter.ToMat = OpenCVFrameConverter.ToMat()

fun detectMotion(frames: Sequence<Frame>, detectionParameters: DetectionParameters): Sequence<Frame> {
    var difference: Mat by Delegates.notNull()
    var mask: Mat by Delegates.notNull()
    var previousFrame: Mat by Delegates.notNull()
    var isFirstFrame = true

    return frames
        .map(converter::convert)
        .map { currentFrame ->
            difference = Mat(currentFrame.size(), CV_8UC1) // Создаем Mat
            cvtColor(currentFrame, difference, COLOR_BGR2GRAY) // Загружаем ч/б кадр
            GaussianBlur(difference, difference, Size(detectionParameters.blurScale.toInt(), detectionParameters.blurScale.toInt()), 0.0) // Размываем

            when (isFirstFrame) {
                true -> {
                    mask = difference
                    isFirstFrame = false
                }

                false -> {
                    subtract(difference, previousFrame, mask)
                    adaptiveThreshold(
                        mask, mask, 255.0, ADAPTIVE_THRESH_MEAN_C, THRESH_BINARY_INV, detectionParameters.threshold, 2.0
                    )
                    detectionContours(mask, currentFrame, detectionParameters.accuracy).forEach {
                        val scalar = Scalar(0.0, 255.0, 0.0, 0.0)
                        rectangle(currentFrame, it.br(), it.tl(), scalar)
                    }
                }
            }
            previousFrame = difference
            converter.convert(currentFrame)
        }.also {
            System.gc()
        }
}

private fun detectionContours(mask: Mat, image: Mat, maxArea: Float): List<Rect> {
    val contours = MatVector()
    findContours(mask, contours, Mat(), RETR_LIST, CHAIN_APPROX_SIMPLE)

    var rectangle: Rect
    val rectangleList: MutableList<Rect> = mutableListOf()

    for (index in 0 until contours.size()) {
        val contour = contours[index]
        val area = contourArea(contour)
        if (area > maxArea) {
            rectangle = boundingRect(contours[index])
            rectangleList.add(rectangle)
            drawContours(image, contours, index.toInt(), Scalar(0.0, 0.0, 255.0, 0.0))
        }
        contour.release()
    }
    return rectangleList
}