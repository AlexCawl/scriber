package org.alexcawl.scriber.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

internal val md_theme_light_primary = Color(0xFF6750A4)
internal val md_theme_light_onPrimary = Color(0xFFFFFFFF)
internal val md_theme_light_onPrimaryContainer = Color(0xFF21005D)
internal val md_theme_light_secondary = Color(0xFF625B71)
internal val md_theme_light_onSecondary = Color(0xFFFFFFFF)
internal val md_theme_light_onSecondaryContainer = Color(0xFF1D192B)
internal val md_theme_light_error = Color(0xFFB3261E)
internal val md_theme_light_onError = Color(0xFFFFFFFF)
internal val md_theme_light_background = Color(0xFFF1F1F1)
internal val md_theme_light_onBackground = Color(0xFF1C1B1F)
internal val md_theme_light_surface = Color(0xFFFFFBFE)
internal val md_theme_light_onSurface = Color(0xFF1C1B1F)
internal val md_theme_dark_primary = Color(0xFFD0BCFF)
internal val md_theme_dark_onPrimary = Color(0xFF381E72)
internal val md_theme_dark_onPrimaryContainer = Color(0xFFEADDFF)
internal val md_theme_dark_secondary = Color(0xFFCCC2DC)
internal val md_theme_dark_onSecondary = Color(0xFF332D41)
internal val md_theme_dark_onSecondaryContainer = Color(0xFFE8DEF8)
internal val md_theme_dark_error = Color(0xFFF2B8B5)
internal val md_theme_dark_onError = Color(0xFF601410)
internal val md_theme_dark_background = Color(0xFF1C1B1F)
internal val md_theme_dark_onBackground = Color(0xFFE6E1E5)
internal val md_theme_dark_surface = Color(0xFF1C1B1F)
internal val md_theme_dark_onSurface = Color(0xFFE6E1E5)

internal val appLightColors = lightColors(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryVariant = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryVariant = md_theme_light_onSecondaryContainer,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface
)

internal val appDarkColors = darkColors(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryVariant = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryVariant = md_theme_dark_onSecondaryContainer,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface
)
