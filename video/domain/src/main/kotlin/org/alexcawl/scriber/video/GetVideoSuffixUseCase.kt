package org.alexcawl.scriber.video

import kotlinx.coroutines.flow.Flow
import org.alexcawl.common.GetUseCase
import org.alexcawl.scriber.data.configuration.ScribeSuffixPropertyRepository
import javax.inject.Inject

class GetVideoSuffixUseCase @Inject constructor(
    private val scribeSuffixPropertyRepository: ScribeSuffixPropertyRepository
) : GetUseCase<Unit, String>() {
    override fun execute(input: Unit): Flow<String> = scribeSuffixPropertyRepository.get()
}