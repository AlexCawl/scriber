package org.alexcawl.scriber.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LinkedCamera
import androidx.compose.material.icons.filled.Screenshot
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.alexcawl.scriber.mvi.compose.StoreScope
import org.alexcawl.scriber.ui.component.input.ToggleFileInputField
import org.alexcawl.scriber.ui.component.layout.DesktopScaffold
import org.alexcawl.scriber.ui.theme.ExtendedTheme
import org.alexcawl.scriber.ui.theme.ThemeScope
import org.alexcawl.scriber.video.VideoScreen

@Composable
fun ApplicationScreen() = StoreScope<ApplicationState, ApplicationAction, ApplicationStore> {
    val state by this.state.collectAsState()
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
                            imageVector = Icons.Default.Screenshot,
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
            is ApplicationState.Loading -> Unit
            is ApplicationState.CameraDetectionScreen -> {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(ExtendedTheme.sizes.large, Alignment.Start)
                    ) {
                        ToggleFileInputField(
                            title = "Select file:",
                            isSingleSelection = true,
                            consume = ::println,
                            modifier = Modifier.weight(1f)
                                .background(Color.LightGray, shape = MaterialTheme.shapes.large).padding(16.dp)
                                .height(64.dp)
                        )
                    }
                }
            }

            is ApplicationState.VideoDetectionScreen -> VideoScreen(modifier = Modifier.fillMaxSize())
        }
    }
}
