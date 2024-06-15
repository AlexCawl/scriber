package org.alexcawl.scriber.video

import org.alexcawl.common.SetUseCase
import org.alexcawl.scriber.data.configuration.ScribeSuffixPropertyRepository
import javax.inject.Inject

class SetVideoSuffixUseCase @Inject constructor(
    private val scribeSuffixPropertyRepository: ScribeSuffixPropertyRepository
) : SetUseCase<String>() {
    override suspend fun invoke(input: String): Result<Unit> = runCatching {
        if (input.isBlank()) {
            throw IllegalArgumentException("Suffix must not be blank")
        }
        if (!input.isText()) {
            throw IllegalArgumentException("Suffix should be text without system symbols")
        }
        scribeSuffixPropertyRepository.set(input)
    }

    private fun String.isText() = this.all { it.isLetterOrDigit() || it == '_' }
}