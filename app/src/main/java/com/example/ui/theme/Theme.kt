package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LankaJobsColorScheme = lightColorScheme(
    primary = SkyPrimary,
    onPrimary = Color.White,
    primaryContainer = SkyPrimaryContainer,
    onPrimaryContainer = OnSkyPrimaryContainer,
    secondary = EmeraldSecondary,
    onSecondary = Color(0xFF003824),
    secondaryContainer = EmeraldContainer,
    onSecondaryContainer = OnEmeraldContainer,
    tertiary = AmberAccent,
    onTertiary = Color(0xFF432B00),
    tertiaryContainer = AmberContainer,
    onTertiaryContainer = OnAmberContainer,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    surfaceContainerHighest = DarkSurfaceElevated,
    surfaceContainerHigh = DarkSurfaceCard,
    outline = DarkBorder,
    outlineVariant = DarkBorderSubtle,
    error = StatusRed,
    onError = Color.White
)

@Composable
fun LankaJobsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LankaJobsColorScheme,
        typography = Typography,
        content = content
    )
}

// Backward-compatible alias for existing tests
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    LankaJobsTheme(content = content)
}

