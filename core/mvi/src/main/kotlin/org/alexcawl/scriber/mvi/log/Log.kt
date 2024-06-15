package org.alexcawl.scriber.mvi.log

data class Log(
    val tag: String,
    val message: String,
    val throwable: Throwable?
) {
    constructor(tag: String, message: String) : this(tag, message, null)

    constructor(tag: String, throwable: Throwable) : this(tag, throwable.message ?: "", throwable)

    constructor(message: String) : this("DEBUG", message, null)
}




