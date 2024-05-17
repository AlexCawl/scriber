package org.alexcawl.scriber.component.input

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragIndicator
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.File

@OptIn(ExperimentalFoundationApi::class)
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
    TooltipArea(
        tooltip = {
            Text(
                text = when (inputType) {
                    FileInputFieldType.DRAG_AND_DROP -> "Drag & Drop"
                    FileInputFieldType.DIALOG -> "Select file from path"
                },
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.background(MaterialTheme.colors.background, MaterialTheme.shapes.small).padding(4.dp)
            )
        }
    ) {
        Button(onClick = { inputType = inputType.toggle() }) {
            Image(
                imageVector = when (inputType) {
                    FileInputFieldType.DRAG_AND_DROP -> Icons.Default.DragIndicator
                    FileInputFieldType.DIALOG -> Icons.Default.FileOpen
                },
                contentDescription = null
            )
        }
    }

    FileInputField(
        title = title,
        consume = consume,
        modifier = Modifier.fillMaxHeight().fillMaxWidth(1f),
        inputType = inputType,
        isSingleSelection = isSingleSelection
    )
}
