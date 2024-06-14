package org.alexcawl.player

import org.alexcawl.common.SetUseCase

class RestartVideoUseCase : SetUseCase<Unit>() {
    override suspend fun invoke(input: Unit): Result<Unit> {
        TODO("Not yet implemented")
    }
}