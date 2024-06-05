package org.alexcawl.scriber.ui.component.input

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.io.File

@Composable
fun FileInputField(
    title: String,
    consume: (File) -> Unit,
    modifier: Modifier = Modifier,
    inputType: FileInputFieldType = FileInputFieldType.DRAG_AND_DROP,
    isSingleSelection: Boolean = true
) = when (inputType) {
    FileInputFieldType.DRAG_AND_DROP -> DragAndDropFileInputField(
        title = title,
        consume = consume,
        isSingleSelection = isSingleSelection,
        modifier = modifier
    )

    FileInputFieldType.DIALOG -> ChooserFileInputField(
        title = title,
        consume = consume,
        isSingleSelection = isSingleSelection,
        modifier = modifier
    )
}
