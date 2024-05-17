package org.alexcawl.scriber.video

import androidx.compose.runtime.Immutable

@Immutable
sealed interface DemoAction {
    data object Increment : DemoAction
    data object Decrement : DemoAction
}