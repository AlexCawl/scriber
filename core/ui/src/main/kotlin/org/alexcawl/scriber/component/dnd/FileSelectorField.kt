package org.alexcawl.scriber.component.dnd

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.io.File

@Composable
fun FileSelectorField(
    modifier: Modifier = Modifier,
    infoLabel: String,
    validate: (File) -> Boolean,
    process: (File) -> Unit
) {

}