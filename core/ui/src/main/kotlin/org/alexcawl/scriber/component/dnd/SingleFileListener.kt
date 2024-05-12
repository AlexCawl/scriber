package org.alexcawl.scriber.component.dnd

import java.io.File

class SingleFileListener(validate: (File) -> Boolean, onFileDropped: (File) -> Unit) :
    DragAndDropListener(validate, onFileDropped, true)