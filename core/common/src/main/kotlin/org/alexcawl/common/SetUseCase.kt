package org.alexcawl.common

abstract class SetUseCase<I> {
    abstract suspend operator fun invoke(input: I): Result<Unit>
}
