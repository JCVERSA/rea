package app.cantas.jcversa.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Light = lightColorScheme(
    primary = Color(0xFF6550A5), onPrimary = Color.White,
    primaryContainer = Color(0xFFEADDFF), onPrimaryContainer = Color(0xFF21005D),
    background = Color(0xFFFCFAFF), onBackground = Color(0xFF1D1924),
    surface = Color(0xFFFFFBFF), onSurface = Color(0xFF1D1924),
    surfaceVariant = Color(0xFFF1ECF5), onSurfaceVariant = Color(0xFF635C6B),
    secondaryContainer = Color(0xFFE6DFEC), onSecondaryContainer = Color(0xFF1D1924),
)
private val Dark = darkColorScheme(
    primary = Color(0xFFD9C7FF), onPrimary = Color(0xFF2E1A57),
    primaryContainer = Color(0xFF4A3374), onPrimaryContainer = Color(0xFFF1E8FF),
    background = Color.Black, onBackground = Color(0xFFF7F2FC),
    surface = Color(0xFF0C0B10), onSurface = Color(0xFFF7F2FC),
    surfaceVariant = Color(0xFF17141E), onSurfaceVariant = Color(0xFFC0B7C9),
    secondaryContainer = Color(0xFF211B2D), onSecondaryContainer = Color(0xFFF7F2FC),
)
@Composable
fun CantasTheme(mode: String = "system", content: @Composable () -> Unit) {
    val dark = when(mode) { "dark" -> true; "light" -> false; else -> isSystemInDarkTheme() }
    MaterialTheme(colorScheme = if(dark) Dark else Light, content = content)
}
