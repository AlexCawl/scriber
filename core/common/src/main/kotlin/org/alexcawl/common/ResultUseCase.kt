package org.alexcawl.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class ResultUseCase<I, O> {
    operator fun invoke(input: I): Flow<Result<O>> = execute(input).flowOn(Dispatchers.IO)

    protected abstract fun execute(input: I): Flow<Result<O>>
}