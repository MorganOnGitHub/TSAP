package com.example.tsap

import androidx.compose.ui.graphics.vector.ImageVector

data class NaviItem(
    var label: String,
    var icon: ImageVector,
    val screen: Screen
)