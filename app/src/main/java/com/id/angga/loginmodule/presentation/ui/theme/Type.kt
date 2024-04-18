package com.id.angga.loginmodule.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.id.angga.loginmodule.R

val monserrat = FontFamily(
    Font(R.font.montserrat_medium, FontWeight.Medium)
)

val arial = FontFamily(
    Font(R.font.arial, FontWeight.Medium)
)
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = monserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    )
)

val monserratMedium = TextStyle(
        fontFamily = monserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
)

val monserratRegular = TextStyle(
    fontFamily = monserrat,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.5.sp
)

val monserratLight = TextStyle(
    fontFamily = monserrat,
    fontWeight = FontWeight.Light,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.5.sp
)

val monserratSemiBold = TextStyle(
    fontFamily = monserrat,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.5.sp
)

val monserratBold = TextStyle(
    fontFamily = monserrat,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    lineHeight = 50.sp,
    letterSpacing = 0.5.sp
)