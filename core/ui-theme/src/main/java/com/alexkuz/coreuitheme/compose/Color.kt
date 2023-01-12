package com.alexkuz.coreuitheme.compose

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalCommonColors = staticCompositionLocalOf { LightCommonColorScheme }

internal val LightCommonColorScheme = CommonColorScheme(

    primary = Color(0xFF4A7AF0),
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFFF5F6FA),
    onPrimaryContainer = Color(0xFF253022),
    primaryInverse = Color(0xFF8FD17D),

    secondary = Color(0xFFB0B9D1),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFC3B6B2),
    onSecondaryContainer = Color(0xFF2B1D19),

    tertiary = Color(0xFFF5F6FA),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFF4F3D1),
    onTertiaryContainer = Color(0xFF312911),

    error = Color(0xFFF04A4A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),

    outline = Color(0xFFCECCCC),
    outlineVariant = Color(0xFFCECCCC),
    scrim = Color(0xFFCECCCC),
    shimmer = Color(0xFFC2C7D6),
    chipBackground = Color(0x1A4A7AF0),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFF5F6FA),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFEEEEEE),
    onSurfaceVariant = Color(0xFF000000),
    surfaceInverse = Color(0xFF303331),
    onSurfaceInverse = Color(0xFFEFF4F1),
    cardBackgroundColor = Color(0xFFE7EAF5),
    symbolPrimary = Color(0xFF1C1B1F),
    symbolPrimaryInverse = Color(0xFFFFFFFF),
    symbolSecondary = Color(0x9A1C1B1F),
    symbolSecondaryInverse = Color(0x9AFFFFFF),
    symbolTertiary = Color(0x651C1B1F),
    symbolTertiaryInverse = Color(0x65FFFFFF),
    symbolDeadline = Color(0xFFFFAB01),
    symbolPlaceholder = Color(0xFF7280AA),
    divider = Color(0x4D373C49)
)

internal val DarkCommonColorScheme = CommonColorScheme(

    primary = Color(0xFF4A7AF0),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF2C3347),
    onPrimaryContainer = Color(0xFF9ABA90),
    primaryInverse = Color(0xFF507844),

    secondary = Color(0xFF7280AA),
    onSecondary = Color(0xFF572818),
    secondaryContainer = Color(0xFF4C332C),
    onSecondaryContainer = Color(0xFFC3B6B2),

    tertiary = Color(0xFF303952),
    onTertiary = Color(0xFFD1CE77),
    tertiaryContainer = Color(0xFF4C401B),
    onTertiaryContainer = Color(0xFFF4F3D1),

    error = Color(0xFFF04A4A),
    onError = Color(0xFF601410),
    errorContainer = Color(0xFF8C1D18),
    onErrorContainer = Color(0xFFF2B8B5),

    outline = Color(0xFF9E9E9E),
    outlineVariant = Color(0xFF9E9E9E),
    scrim = Color(0xFF9E9E9E),
    shimmer = Color(0xFF3F496B),
    chipBackground = Color(0x1A4A7AF0),

    background = Color(0xFF232733),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF2C3347),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF2C3347),
    onSurfaceVariant = Color(0xFFFFFFFF),
    surfaceInverse = Color(0xFFEAEAEA),
    onSurfaceInverse = Color(0xFF303331),
    cardBackgroundColor = Color(0xFF1D212D),

    symbolPrimary = Color(0xFFFFFFFF),
    symbolPrimaryInverse = Color(0xFF1C1B1F),
    symbolSecondary = Color(0x9AFFFFFF),
    symbolSecondaryInverse = Color(0x9A1C1B1F),
    symbolTertiary = Color(0x65FFFFFF),
    symbolTertiaryInverse = Color(0x651C1B1F),
    symbolDeadline = Color(0xFFFFAB01),
    symbolPlaceholder = Color(0xFF7280AA),
    divider = Color(0xFF373C49)
)

internal val DarkColorScheme = DarkCommonColorScheme.toColorScheme()
internal val LightColorScheme = LightCommonColorScheme.toColorScheme()

data class CommonColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val primaryInverse: Color,

    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,

    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,

    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,

    val outline: Color,
    val outlineVariant: Color,
    val scrim: Color,
    val shimmer: Color,
    val chipBackground: Color,

    val background: Color,
    val onBackground: Color,

    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val surfaceInverse: Color,
    val onSurfaceInverse: Color,
    val surfaceTint: Color = primary,
    val cardBackgroundColor: Color,

    val symbolPrimary: Color,
    val symbolPrimaryInverse: Color,
    val symbolSecondary: Color,
    val symbolSecondaryInverse: Color,
    val symbolTertiary: Color,
    val symbolTertiaryInverse: Color,
    val symbolDeadline: Color,
    val symbolPlaceholder: Color,
    val divider: Color,

    val white: Color = Color(0xFFFFFFFF),
    val black: Color = Color(0xFF000000),
    val transparent: Color = Color(0x00000000),
    val gray: Color = Color(0xFF9699A3),
    val label: Color = Color(0xFF7280AA),

    val snackbarContainer: Color = Color(0xDE000000),
) {

    fun toColorScheme() = ColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        inversePrimary = primaryInverse,

        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,

        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,

        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        inverseSurface = surfaceInverse,
        inverseOnSurface = onSurfaceInverse,
        surfaceTint = surfaceTint,

        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,

        outline = outline,
        outlineVariant = outlineVariant,
        scrim = scrim,
    )
}