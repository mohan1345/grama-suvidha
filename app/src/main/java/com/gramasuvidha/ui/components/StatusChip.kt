package com.gramasuvidha.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gramasuvidha.ui.theme.CompletedColor
import com.gramasuvidha.ui.theme.OngoingColor
import com.gramasuvidha.ui.theme.PendingColor

@Composable
fun StatusChip(status: String) {
    val backgroundColor = when (status) {
        "Ongoing" -> OngoingColor
        "Completed" -> CompletedColor
        "Pending" -> PendingColor
        else -> Color.Gray
    }
    
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor.copy(alpha = 0.2f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = status,
            color = backgroundColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
