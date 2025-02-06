package com.compose.mvvm.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.compose.mvvm.R


// Set of Material typography styles to start with
val typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.helvetica_neue_bold))
    ),
    titleMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))
    ),
    titleSmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))
    )
)