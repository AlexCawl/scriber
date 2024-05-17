package org.alexcawl.scriber.video

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.alexcawl.mvi.compose.StoreScope


@Composable
fun DemoScreen() = StoreScope<DemoState, DemoAction, DemoStore> {
    val state by this.state.collectAsState()
    Column {
        Text(
            text = when (state) {
                DemoState.Loading -> "Loading"
                is DemoState.Main -> (state as DemoState.Main).number.toString()
            }
        )
        Row {
            Button(
                onClick = { consume(DemoAction.Increment) }
            ) {
                Text("+")
            }
            Button(
                onClick = { consume(DemoAction.Decrement) }
            ) {
                Text("-")
            }
        }
    }
}

