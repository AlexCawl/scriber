package org.alexcawl.scriber.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LinkedCamera
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.alexcawl.scriber.camera.CameraScreenFeature
import org.alexcawl.scriber.mvi.compose.StoreScope
import org.alexcawl.scriber.ui.component.layout.DesktopScaffold
import org.alexcawl.scriber.ui.theme.ThemeScope
import org.alexcawl.scriber.video.VideoFeature

@Composable
fun ApplicationScreen() = StoreScope<ApplicationState, ApplicationAction, ApplicationStore> {
    val state: ApplicationState by this.state.collectAsState()
    ThemeScope(state.isDarkTheme) {
        ApplicationScreenContent(state)
    }
}

@Composable
private fun ApplicationStore.ApplicationScreenContent(state: ApplicationState) {
    DesktopScaffold(
        modifier = Modifier.fillMaxSize(),
        navigationRail = {
            NavigationRail(
                header = {
                    val logo = painterResource("scriber_logo_colored.svg")
                    Image(
                        painter = logo,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
            ) {
                NavigationRailItem(
                    selected = false,
                    onClick = { consume(ApplicationAction.NavigateToVideo) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.VideoFile,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Video")
                    }
                )

                NavigationRailItem(
                    selected = false,
                    onClick = { consume(ApplicationAction.NavigateToCamera) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.LinkedCamera,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Camera")
                    }
                )

                NavigationRailItem(
                    selected = false,
                    onClick = { consume(ApplicationAction.ToggleTheme) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.LightMode,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Theme")
                    }
                )
            }
        }
    ) {
        when (state) {
            is ApplicationState.Loading -> DescriptionScreen(modifier = Modifier.fillMaxSize())
            is ApplicationState.CameraDetectionScreen -> CameraScreenFeature(modifier = Modifier.fillMaxSize())
            is ApplicationState.VideoDetectionScreen -> VideoFeature(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun DescriptionScreen(
    modifier: Modifier = Modifier
) = Box(modifier = modifier.background(color = MaterialTheme.colors.background), contentAlignment = Alignment.Center) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        val logo: Painter = painterResource("scriber_logo_colored.svg")
        Image(
            painter = logo,
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = "Scriber",
            color = MaterialTheme.colors.onBackground
        )
    }
}