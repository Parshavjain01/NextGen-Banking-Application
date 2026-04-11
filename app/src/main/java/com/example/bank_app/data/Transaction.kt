package com.example.bank_app.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Transaction(
    val title: String,
    val description: String,
    val amount: String,
    val isIncome: Boolean,
    val icon: ImageVector,
    val iconBackground: Color,
    val date: String
)
