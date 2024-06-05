package org.alexcawl.scriber.ui.component.input

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import java.io.File
import javax.swing.JFileChooser

@Composable
fun ChooserFileInputField(
    consume: (File) -> Unit,
    title: String,
    isSingleSelection: Boolean,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var trigger by remember { mutableStateOf(false) }

    Button(
        onClick = { trigger = !trigger },
        modifier = modifier
    ) {
        Text(title)
    }

    if (trigger) {
        DisposableEffect("FileInputField") {
            val job = scope.launch(Dispatchers.Swing) {
                val fileChooser = JFileChooser().apply {
                    dialogTitle = title
                    isMultiSelectionEnabled = !isSingleSelection
                }
                val returned: Int = fileChooser.showDialog(null, title)
                when (returned) {
                    JFileChooser.APPROVE_OPTION -> when (isSingleSelection) {
                        true -> consume(fileChooser.selectedFile)
                        false -> fileChooser.selectedFiles.forEach(consume)
                    }

                    else -> Unit
                }
                trigger = false
            }
            onDispose {
                trigger = false
                job.cancel()
            }
        }
    }
}