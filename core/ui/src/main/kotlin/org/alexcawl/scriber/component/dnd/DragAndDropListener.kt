package org.alexcawl.scriber.component.dnd

import java.awt.dnd.*
import java.io.File

@Suppress("UNCHECKED_CAST")
abstract class DragAndDropListener(
    private val validate: (File) -> Boolean,
    private val onFileDropped: (File) -> Unit,
    private val isSingleFile: Boolean
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
                        if (validate(file)) {
                            onFileDropped(file)
                            if (isSingleFile) {
                                break
                            }
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