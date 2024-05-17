package org.alexcawl.scriber.component.input

enum class FileInputFieldType {
    DRAG_AND_DROP,
    DIALOG;

    fun toggle(): FileInputFieldType {
        return when (this) {
            DRAG_AND_DROP -> DIALOG
            DIALOG -> DRAG_AND_DROP
        }
    }
}