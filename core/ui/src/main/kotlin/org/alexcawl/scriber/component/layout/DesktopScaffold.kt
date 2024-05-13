package org.alexcawl.scriber.component.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DesktopScaffold(
    modifier: Modifier = Modifier,
    navigationRail: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.Top,
    horizontalArrangement = Arrangement.Start
) {
    navigationRail?.let { it() }
    content()
}

