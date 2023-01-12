package com.alexkuz.coreuitheme.compose

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

internal val LocalCommonTypography = staticCompositionLocalOf { DefaultCommonTypography }

internal val DefaultCommonTypography = CommonTypography(
    h1 = TextStyle(
        fontSize = 96.sp,
        letterSpacing = (-0.02).em,
        fontFamily = FontFamilyW300,
        lineHeight = 112.sp,
    ),
    h2 = TextStyle(
        fontSize = 60.sp,
        letterSpacing = (-0.01).em,
        fontFamily = FontFamilyW300,
        lineHeight = 72.sp,
    ),
    h3 = TextStyle(
        fontSize = 48.sp,
        fontFamily = FontFamilyW400,
        lineHeight = 56.sp,
    ),
    h4 = TextStyle(
        fontSize = 34.sp,
        fontFamily = FontFamilyW400,
        lineHeight = 36.sp,
    ),
    h5 = TextStyle(
        fontSize = 24.sp,
        letterSpacing = 0.01.em,
        fontFamily = FontFamilyW400,
        lineHeight = 24.sp,
    ),
    h6 = TextStyle(
        fontSize = 20.sp,
        letterSpacing = 0.01.em,
        fontFamily = FontFamilyW500,
        lineHeight = 24.sp,
    ),
    subtitle1 = TextStyle(
        fontSize = 16.sp,
        letterSpacing = 0.01.em,
        fontFamily = FontFamilyW400,
        lineHeight = 24.sp,
    ),
    subtitle2 = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 0.01.em,
        fontFamily = FontFamilyW500,
        lineHeight = 24.sp,
    ),
    body1 = TextStyle(
        fontSize = 16.sp,
        letterSpacing = 0.03.em,
        fontFamily = FontFamilyW400,
        lineHeight = 24.sp,
    ),
    body2 = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 0.02.em,
        fontFamily = FontFamilyW400,
        lineHeight = 20.sp,
    ),
    button = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 0.01.em,
        fontFamily = FontFamilyW500,
        lineHeight = 16.sp,
    ),
    caption = TextStyle(
        fontSize = 12.sp,
        letterSpacing = 0.03.em,
        fontFamily = FontFamilyW400,
        lineHeight = 16.sp,
    ),
    overline = TextStyle(
        fontSize = 10.sp,
        letterSpacing = 0.15.em,
        fontFamily = FontFamilyW500,
        lineHeight = 16.sp,
    ),
)

data class CommonTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
    val overline: TextStyle,
)