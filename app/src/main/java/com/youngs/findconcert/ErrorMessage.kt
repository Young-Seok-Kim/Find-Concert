package com.youngs.findconcert

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ErrorMessage(message: String) {
    Text(
        text = "Error: $message",
        color = Color.Red,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}
