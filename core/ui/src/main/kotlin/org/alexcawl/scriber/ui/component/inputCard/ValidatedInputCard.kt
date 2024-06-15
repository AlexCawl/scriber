package org.alexcawl.scriber.ui.component.inputCard

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> ValidatedInputCard(
    initialValue: T,
    valueMapper: (T) -> String,
    valueValidator: (String) -> Result<T>,
    onValueSubmit: (T) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit = {},
    errorLabel: @Composable () -> Unit = {}
) = Card(modifier = modifier) {
    val initialText: String = valueMapper(initialValue)
    var text: String by remember { mutableStateOf(initialText) }
    var isError: Boolean by remember { mutableStateOf(false) }

    LaunchedEffect(initialValue) {
        text = initialText
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = when (isError) {
                true -> errorLabel
                false -> label
            },
            isError = isError,
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = {
                val validationResult: Result<T> = valueValidator(text)
                if (validationResult.isSuccess) {
                    onValueSubmit(validationResult.getOrNull()!!)
                    isError = false
                } else {
                    isError = true
                }
            },
            enabled = text != initialText,
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Text("Submit")
        }
    }
}