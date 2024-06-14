package org.alexcawl.configuration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.alexcawl.scriber.mvi.compose.StoreScope
import org.alexcawl.scriber.ui.component.inputCard.ValidatedInputCard

private val intValueMapper: (Int) -> String = Int::toString
private val floatValueMapper: (Float) -> String = Float::toString

private val intValueValidator: (String) -> Result<Int> = { runCatching { it.toInt() } }
private val floatValueValidator: (String) -> Result<Float> = { runCatching { it.toFloat() } }


@Composable
fun ConfigurationScreen(
    modifier: Modifier = Modifier
) = StoreScope<ConfigurationScreenState, ConfigurationScreenAction, ConfigurationScreenStore> {
    val state: ConfigurationScreenState by this.state.collectAsState()
    ConfigurationScreenContent(
        state = state,
        event = this::consume,
        modifier = modifier
    )
}

@Composable
private fun ConfigurationScreenContent(
    state: ConfigurationScreenState,
    event: (ConfigurationScreenAction) -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier.background(MaterialTheme.colors.background),
    verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
    horizontalAlignment = Alignment.Start
) {
    ValidatedInputCard(
        initialValue = state.accuracy,
        valueMapper = floatValueMapper,
        valueValidator = floatValueValidator,
        onValueSubmit = { event(ConfigurationScreenAction.SetAccuracy(it)) },
        label = { Text("Accuracy") },
        errorLabel = { Text("Invalid value format!") }
    )
    ValidatedInputCard(
        initialValue = state.blurScale,
        valueMapper = floatValueMapper,
        valueValidator = floatValueValidator,
        onValueSubmit = { event(ConfigurationScreenAction.SetBlurScale(it)) },
        label = { Text("Blur scale") },
        errorLabel = { Text("Invalid value format!") },
    )
    ValidatedInputCard(
        initialValue = state.threshold,
        valueMapper = intValueMapper,
        valueValidator = intValueValidator,
        onValueSubmit = { event(ConfigurationScreenAction.SetThreshold(it)) },
        label = { Text("Threshold") },
        errorLabel = { Text("Invalid value format!") },
    )
}