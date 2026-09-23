package com.example.ui.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AmberAccent
import com.example.ui.theme.AmberContainer
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkSurfaceElevated
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldSecondary
import com.example.ui.theme.SkyPrimary
import com.example.ui.theme.SkyPrimaryContainer
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextSecondary

@Composable
fun StatusBadge(
    text: String,
    containerColor: Color = DarkSurfaceElevated,
    contentColor: Color = TextSecondary,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(containerColor)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = text,
            color = contentColor,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1
        )
    }
}

@Composable
fun FeaturedBadge(modifier: Modifier = Modifier) {
    StatusBadge(
        text = "★ FEATURED",
        containerColor = AmberContainer.copy(alpha = 0.85f),
        contentColor = AmberAccent,
        modifier = modifier
    )
}

@Composable
fun RemoteBadge(modifier: Modifier = Modifier) {
    StatusBadge(
        text = "⚡ REMOTE",
        containerColor = EmeraldContainer.copy(alpha = 0.85f),
        contentColor = EmeraldSecondary,
        modifier = modifier
    )
}

@Composable
fun EmploymentTypeBadge(type: String, modifier: Modifier = Modifier) {
    StatusBadge(
        text = type,
        containerColor = SkyPrimaryContainer.copy(alpha = 0.6f),
        contentColor = SkyPrimary,
        modifier = modifier
    )
}

@Composable
fun CategoryBadge(category: String, modifier: Modifier = Modifier) {
    StatusBadge(
        text = category,
        containerColor = DarkBorder.copy(alpha = 0.5f),
        contentColor = TextMuted,
        modifier = modifier
    )
}
