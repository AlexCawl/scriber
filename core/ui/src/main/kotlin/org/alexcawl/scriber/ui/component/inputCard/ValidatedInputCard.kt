package org.alexcawl.scriber.ui.component.inputCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    var text: String by remember { mutableStateOf(valueMapper(initialValue)) }
    var isError: Boolean by remember { mutableStateOf(false) }

    LaunchedEffect(initialValue) {
        text = valueMapper(initialValue)
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
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
            modifier = Modifier.weight(1f)
        ) {
            Text("Submit")
        }
    }
}