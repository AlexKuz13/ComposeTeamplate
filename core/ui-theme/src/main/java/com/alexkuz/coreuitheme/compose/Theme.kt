package com.alexkuz.coreuitheme.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun AppTheme(lightTheme: Boolean = !isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val commonColors = when {
        lightTheme -> LightCommonColorScheme
        else -> DarkCommonColorScheme
    }
    val colorScheme = when {
        lightTheme -> LightColorScheme
        else -> DarkColorScheme
    }

    CompositionLocalProvider(
        LocalCommonColors provides commonColors,
        LocalCommonTypography provides DefaultCommonTypography,
        LocalLightTheme provides lightTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            shapes = AppShapes,
            content = content,
        )
    }
}

internal val LocalLightTheme = staticCompositionLocalOf { false }

object AppTheme {

    val isLightTheme: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalLightTheme.current

    val commonColorScheme: CommonColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalCommonColors.current

    val commonTypography: CommonTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalCommonTypography.current

    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}