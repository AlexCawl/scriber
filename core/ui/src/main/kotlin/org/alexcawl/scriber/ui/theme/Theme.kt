package org.alexcawl.scriber.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import org.alexcawl.scriber.ui.theme.extended.ExtendedColor
import org.alexcawl.scriber.ui.theme.extended.ExtendedSize

internal val LocalExtendedSizes = staticCompositionLocalOf { ExtendedSize() }
internal val LocalExtendedColors = staticCompositionLocalOf { ExtendedColor() }

@Composable
fun ThemeScope(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val extendedSizes = ExtendedSize()
    val extendedColors = ExtendedColor()
    val colorScheme = when (darkTheme) {
        true -> appDarkColors
        false -> appLightColors
    }

    CompositionLocalProvider(
        LocalExtendedSizes provides extendedSizes,
        LocalExtendedColors provides extendedColors
    ) {
        MaterialTheme(
            colors = colorScheme,
            typography = typography,
            content = content
        )
    }
}

object ExtendedTheme {
    val sizes: ExtendedSize
        @Composable get() = LocalExtendedSizes.current

    val colors: ExtendedColor
        @Composable get() = LocalExtendedColors.current
}