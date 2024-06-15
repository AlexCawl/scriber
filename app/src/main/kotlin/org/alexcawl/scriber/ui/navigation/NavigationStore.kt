package org.alexcawl.scriber.ui.navigation

import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.mvi.core.Store
import javax.inject.Inject

class NavigationStore @Inject constructor(
    scope: CoroutineScope
) : Store<NavigationState, NavigationAction>(scope, NavigationState.Loading) {
    override fun handle(event: NavigationAction) = when (event) {
        NavigationAction.NavigateToLoading -> task { reduce { NavigationState.Loading } }
        NavigationAction.NavigateToCamera -> task { reduce { NavigationState.CameraScreen } }
        NavigationAction.NavigateToVideo -> task { reduce { NavigationState.VideoScreen } }
    }
}