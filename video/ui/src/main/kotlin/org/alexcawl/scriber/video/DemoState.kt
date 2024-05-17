package org.alexcawl.scriber.video

import androidx.compose.runtime.Immutable

@Immutable
sealed interface DemoState {
    data object Loading : DemoState
    data class Main(val number: Int) : DemoState
}