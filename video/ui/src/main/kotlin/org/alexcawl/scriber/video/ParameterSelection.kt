package org.alexcawl.scriber.video

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun ParameterSelection(
    label: String,
    value: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) = Row(modifier) {
    Text(label)
    TextField(
        value = value,
        onValueChange = onSelected
    )
}