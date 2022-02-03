package ru.webanimal.test53_compose_animations.core.navigation

import androidx.compose.runtime.Composable
import ru.webanimal.test53_compose_animations.core.navigation.ScreenType.Clock
import ru.webanimal.test53_compose_animations.feature_clock.ClockScreen

interface Navigator {

    @Composable
    fun NavigateTo(type: ScreenType)
}

class NavigatorImpl : Navigator {

    @Composable
    override fun NavigateTo(type: ScreenType) = when (type) {
        Clock -> ClockScreen()
    }
}

sealed class ScreenType {
    object Clock : ScreenType()
}