package org.alexcawl.scriber.component.dnd

import java.io.File

class MultiFileListener(validate: (File) -> Boolean, onFileDropped: (File) -> Unit) :
    DragAndDropListener(validate, onFileDropped, false)