package org.alexcawl.scriber

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LinkedCamera
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.alexcawl.mvi.compose.StoreFactoryScope
import org.alexcawl.scriber.component.input.ToggleFileInputField
import org.alexcawl.scriber.component.layout.DesktopScaffold
import org.alexcawl.scriber.theme.ExtendedTheme
import org.alexcawl.scriber.theme.ScriberTheme
import org.alexcawl.scriber.video.DemoScreen

@Composable
@Preview
fun App() {
    ScriberTheme {
        DesktopScaffold(
            modifier = Modifier.fillMaxSize(),
            navigationRail = {
                NavigationRail(
                    header = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null
                        )
                    }
                ) {
                    NavigationRailItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Default.VideoFile,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text("Home")
                        }
                    )

                    NavigationRailItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Default.LinkedCamera,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text("Favorite")
                        }
                    )
                }
            }
        ) {
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
                        modifier = Modifier.weight(1f).background(Color.LightGray, shape = MaterialTheme.shapes.large).padding(16.dp).height(64.dp)
                    )
                }
                DemoScreen()
            }
        }
    }
}

fun main() = run {
    val applicationComponent = DaggerApplicationComponent.create()
    application {
        val logo = painterResource("scriber_logo_colored.svg")
        StoreFactoryScope(applicationComponent.storeFactory) {
            Window(onCloseRequest = ::exitApplication, icon = logo) {
                App()
            }
        }
    }
}
