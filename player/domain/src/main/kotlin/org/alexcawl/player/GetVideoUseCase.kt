package org.alexcawl.player

import kotlinx.coroutines.flow.Flow
import org.alexcawl.common.GetUseCase

class GetVideoUseCase(
    private val playerService: PlayerService
) : GetUseCase<Unit, Sequence<ByteArray>>() {
    override fun execute(input: Unit): Flow<Sequence<ByteArray>> = playerService.get()
}