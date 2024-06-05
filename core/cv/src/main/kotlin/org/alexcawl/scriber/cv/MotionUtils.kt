package org.alexcawl.scriber.cv

import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.OpenCVFrameConverter
import org.bytedeco.opencv.global.opencv_core
import org.bytedeco.opencv.global.opencv_core.subtract
import org.bytedeco.opencv.global.opencv_imgproc.*
import org.bytedeco.opencv.opencv_core.*

private val converter: OpenCVFrameConverter.ToMat = OpenCVFrameConverter.ToMat()

fun detectMotion(frames: Sequence<Frame>): Result<Sequence<Frame>> = runCatching {
    var outerBox: Mat
    var diff_frame: Mat? = null
    var tempon_frame: Mat? = null
    var isFirstIteration = true
    var image: Mat

    sequence {
        frames.map(converter::convert).forEach { frame ->
            image = frame.clone()
            outerBox = Mat(frame.size(), opencv_core.CV_8UC1)
            cvtColor(frame, outerBox, COLOR_BGR2GRAY)
            GaussianBlur(outerBox, outerBox, Size(5, 5), 0.0)

            when (isFirstIteration) {
                true -> {
                    tempon_frame = Mat(outerBox.size(), opencv_core.CV_8UC1)
                    diff_frame = outerBox.clone()
                }
                false -> {
                    subtract(outerBox, tempon_frame, diff_frame)
                    adaptiveThreshold(
                        diff_frame, diff_frame, 255.0,
                        ADAPTIVE_THRESH_MEAN_C,
                        THRESH_BINARY_INV, 5, 2.0
                    )
                    detectContours(diff_frame!!, image).forEach {
                        val scalar = Scalar(0.0, 255.0, 0.0, 0.0)
                        rectangle(image, it.br(), it.tl(), scalar)
                    }
                }
            }
            isFirstIteration = false
            yield(converter.convert(image))
        }
    }
}

private fun detectContours(difference: Mat, source: Mat): List<Rect> {
    val v = Mat()
    val vv = difference.clone()
    val contours = MatVector()
    findContours(vv, contours, v, RETR_LIST, CHAIN_APPROX_SIMPLE)
    val maxArea = 100.0
    val rects = mutableListOf<Rect>()
    for (idx in 0 until contours.size()) {
        val contour = contours[idx]
        val contourarea = contourArea(contour)
        if (contourarea > maxArea) {
            val maxAreaIdx = idx.toInt()
            val rectangle = boundingRect(contours[maxAreaIdx.toLong()])
            rects.add(rectangle)
            drawContours(
                source,
                contours,
                maxAreaIdx,
                Scalar(0.0, 0.0, 255.0, 0.0)
            )
        }
    }
    v.release()
    return rects.toList()
}