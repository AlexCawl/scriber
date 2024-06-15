package org.alexcawl.scriber.video

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.common.GetUseCase
import javax.inject.Inject

class GetDetectedVideoUseCase @Inject constructor(
    private val getVideoDetectionManager: GetVideoDetectionManager,
) : GetUseCase<Unit, Sequence<ByteArray>>() {
    override fun execute(input: Unit): Flow<Sequence<ByteArray>> = flow {
        getVideoDetectionManager(Unit).collect {
            emit(
                when (it) {
                    null -> emptySequence()
                    else -> it.getMotionDetectedVideo()
                }
            )
        }
    }
}