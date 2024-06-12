package org.alexcawl.scriber.ui.navigation

import androidx.compose.runtime.Immutable

@Immutable
sealed interface NavigationState {
    @Immutable
    data object Loading : NavigationState

    @Immutable
    data object CameraScreen : NavigationState

    @Immutable
    data object VideoScreen : NavigationState
}