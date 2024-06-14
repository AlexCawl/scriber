package org.alexcawl.common

sealed class Source {
    data object None : Source()

    data object FileSource : Source()
}
