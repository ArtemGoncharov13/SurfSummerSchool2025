package ru.adgoncharov.surfsummerschool2025.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.adgoncharov.surfsummerschool2025.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val interFontFamily = FontFamily(
    Font(R.font.inter_light, FontWeight.Light),
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_black, FontWeight.Black),
    Font(R.font.inter_black_italic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.inter_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.inter_extra_bold, FontWeight.ExtraBold),
    Font(R.font.inter_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.inter_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.inter_thin, FontWeight.Thin),
    Font(R.font.inter_thin_italic, FontWeight.Thin, FontStyle.Italic),
)