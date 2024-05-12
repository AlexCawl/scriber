package org.alexcawl.scriber.component.dnd

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import java.awt.dnd.DropTarget
import java.io.File
import javax.swing.JLabel
import javax.swing.SwingConstants

@Composable
fun DragAndDropField(
    infoLabel: String,
    validate: (File) -> Boolean,
    process: (File) -> Unit,
    modifier: Modifier = Modifier
) = BoxWithConstraints(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    SwingPanel(
        modifier = Modifier.sizeIn(minWidth, minHeight, maxWidth, maxHeight),
        background = Color.Transparent,
        factory = { swingDragAndDropField(infoLabel, validate, process) }
    )
}

private fun swingDragAndDropField(
    label: String,
    validate: (File) -> Boolean,
    process: (File) -> Unit
): JLabel = JLabel(label, SwingConstants.CENTER).apply {
    val listener = SingleFileListener(validate, process)
    DropTarget(this, listener)
}