package org.alexcawl.scriber.cv

import org.bytedeco.javacv.FFmpegFrameRecorder
import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.FrameGrabber
import org.bytedeco.javacv.FrameRecorder
import java.io.File

fun downloadVideo(grabber: FrameGrabber): Result<Sequence<Frame>> = runCatching {
    sequence {
        grabber.start()
        while (true) {
            yield(grabber.grabFrame() ?: break)
        }
        grabber.stop()
    }
}

fun uploadVideo(recorder: FrameRecorder, frames: Sequence<Frame>): Result<Unit> = runCatching {
    recorder.start()
    frames.forEach {
        recorder.record(it)
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