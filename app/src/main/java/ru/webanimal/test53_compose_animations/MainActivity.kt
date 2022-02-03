package ru.webanimal.test53_compose_animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import ru.webanimal.test53_compose_animations.core.navigation.Navigator
import ru.webanimal.test53_compose_animations.core.navigation.NavigatorImpl
import ru.webanimal.test53_compose_animations.core.navigation.ScreenType

class MainActivity : ComponentActivity() {

    private val navigator: Navigator by lazy { NavigatorImpl() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(navigator)
        }
    }
}

@Composable
fun MyApp(navigator: Navigator) {
    navigator.NavigateTo(type = ScreenType.Clock)
}