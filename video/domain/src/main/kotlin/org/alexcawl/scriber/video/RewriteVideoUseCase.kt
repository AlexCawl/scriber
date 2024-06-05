package org.alexcawl.scriber.video

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class RewriteVideoUseCase(
    private val scope: CoroutineScope,
    private val analyzer: VideoAnalyzer
) {
    operator fun invoke(from: File, to: File) {
        scope.launch(Dispatchers.IO) {
            val result = analyzer.downloadMotionDetectedVideo(from, to)
            println(result.exceptionOrNull())
        }
    }
}