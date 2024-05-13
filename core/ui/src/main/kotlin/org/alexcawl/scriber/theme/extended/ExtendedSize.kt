package org.alexcawl.scriber.theme.extended

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ExtendedSize(
    val small: Dp = 48.dp,
    val medium: Dp = 96.dp,
    val large: Dp = 192.dp
)