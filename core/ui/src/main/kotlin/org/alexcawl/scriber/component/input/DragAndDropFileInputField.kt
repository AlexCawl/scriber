package org.alexcawl.scriber.component.input

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import java.awt.dnd.*
import java.io.File
import javax.swing.JLabel
import javax.swing.SwingConstants

@Composable
fun DragAndDropFileInputField(
    consume: (File) -> Unit,
    title: String,
    isSingleSelection: Boolean,
    modifier: Modifier = Modifier
) = BoxWithConstraints(
    modifier = modifier,
    contentAlignment = Alignment.Center
) {
    SwingPanel(
        modifier = Modifier.sizeIn(minWidth, minHeight, maxWidth, maxHeight),
        background = Color.Transparent,
        factory = { swingDragAndDropField(title, consume, isSingleSelection) }
    )
}

private fun swingDragAndDropField(
    label: String,
    process: (File) -> Unit,
    isSingleFile: Boolean
): JLabel = JLabel(label, SwingConstants.CENTER).apply {
    val listener = DragAndDropAdapter(process, isSingleFile)
    DropTarget(this, listener)
}

@Suppress("UNCHECKED_CAST")
private class DragAndDropAdapter(
    private val onFileDropped: (File) -> Unit,
    private val isSingleFile: Boolean = true
) : DropTargetListener {
    override fun dragEnter(dtde: DropTargetDragEvent) = Unit

    override fun dragOver(dtde: DropTargetDragEvent) = Unit

    override fun dropActionChanged(dtde: DropTargetDragEvent) = Unit

    override fun dragExit(dte: DropTargetEvent) = Unit

    override fun drop(event: DropTargetDropEvent) {
        event.acceptDrop(DnDConstants.ACTION_COPY)
        val transferable = event.transferable
        val flavors = transferable.transferDataFlavors
        for (flavor in flavors) {
            try {
                if (flavor.isFlavorJavaFileListType) {
                    val files = transferable.getTransferData(flavor) as List<File>
                    for (file in files) {
                        onFileDropped(file)
                        if (isSingleFile) {
                            break
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        event.dropComplete(true)
    }
}