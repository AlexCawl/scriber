package org.alexcawl.scriber

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.alexcawl.scriber.component.input.FileInputField
import org.alexcawl.scriber.component.input.FileInputFieldType
import org.alexcawl.scriber.component.layout.DesktopScaffold
import org.alexcawl.scriber.theme.ExtendedTheme
import org.alexcawl.scriber.theme.ScriberTheme

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
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(ExtendedTheme.sizes.large)
            ) {
                FileInputField(
                    title = "Select file:",
                    inputType = FileInputFieldType.DRAG_AND_DROP,
                    isSingleSelection = false,
                    consume = {
                        println("From DRAG_AND_DROP: $it")
                    },
                    modifier = Modifier.weight(1f)
                )

                FileInputField(
                    title = "Select file:",
                    inputType = FileInputFieldType.DIALOG,
                    isSingleSelection = false,
                    consume = {
                        println("From DIALOG: $it")
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}