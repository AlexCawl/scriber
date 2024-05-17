package org.alexcawl.scriber.component.input

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragIndicator
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.File

@Composable
fun ToggleFileInputField(
    title: String,
    consume: (File) -> Unit,
    modifier: Modifier = Modifier,
    isSingleSelection: Boolean = true
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.Start)
) {
    var inputType: FileInputFieldType by remember { mutableStateOf(FileInputFieldType.DIALOG) }
    Button(onClick = { inputType = inputType.toggle() }) {
        Image(
            imageVector = when (inputType) {
                FileInputFieldType.DRAG_AND_DROP -> Icons.Default.DragIndicator
                FileInputFieldType.DIALOG -> Icons.Default.FileOpen
            },
            contentDescription = null
        )
    }
    FileInputField(
        title = title,
        consume = consume,
        modifier = Modifier.fillMaxHeight().fillMaxWidth(1f),
        inputType = inputType,
        isSingleSelection = isSingleSelection
    )
}
