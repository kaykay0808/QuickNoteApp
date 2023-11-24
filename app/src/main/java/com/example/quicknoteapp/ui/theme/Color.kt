package com.example.quicknoteapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val MaterialTheme.rowBackGroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGray else Pink80 // or LightGray

val MaterialTheme.rowTextColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else DarkGray


