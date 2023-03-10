package com.alexkuz.coreuitheme.compose

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.alexkuz.coreuitheme.R

internal val FontFamilyW100 = FontFamily(
    listOf(
        Font(R.font.thin, weight = FontWeight.W100, style = FontStyle.Normal),
        Font(R.font.thin_italic, weight = FontWeight.W100, style = FontStyle.Italic),
    )
)
internal val FontFamilyW200 = FontFamily(
    listOf(
        Font(R.font.light, weight = FontWeight.W200, style = FontStyle.Normal),
        Font(R.font.light_italic, weight = FontWeight.W200, style = FontStyle.Italic),
    )
)
internal val FontFamilyW300 = FontFamily(
    listOf(
        Font(R.font.light, weight = FontWeight.W300, style = FontStyle.Normal),
        Font(R.font.light_italic, weight = FontWeight.W300, style = FontStyle.Italic),
    )
)
internal val FontFamilyW400 = FontFamily(
    listOf(
        Font(R.font.regular, weight = FontWeight.W400, style = FontStyle.Normal),
        Font(R.font.regular_italic, weight = FontWeight.W400, style = FontStyle.Italic),
    )
)
internal val FontFamilyW500 = FontFamily(
    listOf(
        Font(R.font.medium, weight = FontWeight.W500, style = FontStyle.Normal),
        Font(R.font.medium_italic, weight = FontWeight.W500, style = FontStyle.Italic),
    )
)
internal val FontFamilyW700 = FontFamily(
    listOf(
        Font(R.font.bold, weight = FontWeight.W700, style = FontStyle.Normal),
        Font(R.font.bold_italic, weight = FontWeight.W700, style = FontStyle.Italic),
    )
)