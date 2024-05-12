package org.alexcawl.scriber

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.alexcawl.scriber.component.input.FileInputField
import org.alexcawl.scriber.component.input.FileInputFieldType

@Composable
@Preview
fun App() {
    MaterialTheme {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(64.dp)
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

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
