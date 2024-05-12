package org.alexcawl.scriber.component.input

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.io.File

enum class FileInputFieldType {
    DRAG_AND_DROP, DIALOG
}

@Composable
fun FileInputField(
    title: String,
    consume: (File) -> Unit,
    modifier: Modifier = Modifier,
    inputType: FileInputFieldType = FileInputFieldType.DRAG_AND_DROP,
    isSingleSelection: Boolean = true
) = Box(modifier) {
    when (inputType) {
        FileInputFieldType.DRAG_AND_DROP -> DragAndDropFileInputField(
            title = title, consume = consume, isSingleSelection = isSingleSelection, modifier = Modifier.fillMaxSize()
        )

        FileInputFieldType.DIALOG -> ChooserFileInputField(
            title = title, consume = consume, isSingleSelection = isSingleSelection, modifier = Modifier.fillMaxSize()
        )
    }
}
