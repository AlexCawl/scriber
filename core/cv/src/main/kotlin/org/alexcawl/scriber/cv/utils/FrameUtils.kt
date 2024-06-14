package org.alexcawl.scriber.cv.utils

import org.bytedeco.javacv.FFmpegFrameRecorder
import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.FrameRecorder
import java.io.File

fun downloadVideo(grabber: FrameGrabber): Sequence<Frame> = sequence {
    grabber.start()
    while (true) {
        yield(grabber.grabFrame() ?: break)
    }
    grabber.stop()
}

fun uploadVideo(recorder: FrameRecorder, frames: Sequence<Frame>) {
    recorder.start()
    frames.forEach {
        it.use(recorder::record)
    }
    recorder.stop()
}

fun produceEqualRecorder(video: File, grabber: FrameGrabber): FrameRecorder {
    grabber.start()
    val recorder = FFmpegFrameRecorder(video, 0).apply {
        videoCodec = grabber.videoCodec
        format = grabber.format
        frameRate = grabber.frameRate
        videoBitrate = grabber.videoBitrate
        imageWidth = grabber.imageWidth
        imageHeight = grabber.imageHeight
        audioChannels = grabber.audioChannels
    }
    grabber.stop()
    return recorder
}