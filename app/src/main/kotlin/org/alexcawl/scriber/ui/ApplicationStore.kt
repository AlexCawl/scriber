package org.alexcawl.scriber.ui

import kotlinx.coroutines.CoroutineScope
import org.alexcawl.scriber.data.theme.ThemeRepository
import org.alexcawl.scriber.mvi.core.Store
import org.alexcawl.scriber.ui.navigation.NavigationAction
import org.alexcawl.scriber.ui.navigation.NavigationState
import org.alexcawl.scriber.ui.navigation.NavigationStore
import javax.inject.Inject

class ApplicationStore @Inject constructor(
    scope: CoroutineScope,
    private val navigationStore: NavigationStore,
    private val themeRepository: ThemeRepository
) : Store<ApplicationState, ApplicationAction>(scope, ApplicationState.Loading(false)) {
    init {
        task {
            navigationStore.state.collect { destination: NavigationState ->
                reduce {
                    when (destination) {
                        NavigationState.Loading -> ApplicationState.Loading(it.isDarkTheme)
                        NavigationState.CameraScreen -> ApplicationState.CameraDetectionScreen(it.isDarkTheme)
                        NavigationState.VideoScreen -> ApplicationState.VideoDetectionScreen(it.isDarkTheme)
                    }
                }
            }
        }
        task {
            themeRepository.get().collect { theme: Boolean ->
                reduce {
                    when (it) {
                        is ApplicationState.Loading -> ApplicationState.Loading(theme)
                        is ApplicationState.CameraDetectionScreen -> ApplicationState.CameraDetectionScreen(theme)
                        is ApplicationState.VideoDetectionScreen -> ApplicationState.VideoDetectionScreen(theme)
                    }
                }
            }
        }
    }

    override fun handle(event: ApplicationAction) {
        when (event) {
            ApplicationAction.NavigateToCamera -> task {
                navigationStore.consume(NavigationAction.NavigateToCamera)
            }

            ApplicationAction.NavigateToVideo -> task {
                navigationStore.consume(NavigationAction.NavigateToVideo)
            }

            ApplicationAction.ToggleTheme -> task {
                themeRepository.toggle()
            }
        }
    }

    override fun onDispose() {
        super.onDispose()
        navigationStore.onDispose()
    }
}
