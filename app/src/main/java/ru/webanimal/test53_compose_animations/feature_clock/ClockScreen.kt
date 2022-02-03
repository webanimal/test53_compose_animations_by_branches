package ru.webanimal.test53_compose_animations.feature_clock

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.webanimal.test53_compose_animations.core.theme.AppTheme

@Composable
fun ClockScreen() {
    AppTheme {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String) {
    Surface(color = MaterialTheme.colors.background) {
        Text(text = "Hello $name!", color = MaterialTheme.colors.onSurface)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DarkThemePreview")
@Composable
fun DefaultPreview() {
    ClockScreen()
}