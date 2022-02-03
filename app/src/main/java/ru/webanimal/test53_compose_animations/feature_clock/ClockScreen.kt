package ru.webanimal.test53_compose_animations.feature_clock

import android.content.res.Configuration
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.webanimal.test53_compose_animations.core.theme.AppTheme

@Composable
fun ClockScreen() {
    AppTheme {
        Clock(
            modifier = Modifier
                .aspectRatio(1.0f)
                .padding(64.dp)
        )
    }
}

@Composable
fun Clock(modifier: Modifier = Modifier) {
    val watchColor = MaterialTheme.colors.onBackground
    val hourHandColor = MaterialTheme.colors.onBackground
    val minuteHandColor = Color.Red

    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        0f,
        1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, 0, easing = LinearEasing)
        )
    )
    var previousProgress = remember { mutableStateOf(0f) }
    val currentAngle = remember { mutableStateOf(0f) }

    Canvas(modifier = modifier) {
        val middle = Offset(size.minDimension / 2, size.minDimension / 2)
        val clockBorderStroke = Stroke(4.dp.toPx())
        val hourHandWidth = 12.dp.toPx()
        val hourHandHeight = Offset(size.minDimension / 2, 36.dp.toPx())
        val minuteHandWidth = 8.dp.toPx()
        val minuteHandHeight = Offset(size.minDimension / 2, 12.dp.toPx())

        withTransform(
            transformBlock = {
                if (previousProgress.value > progress) {
                    currentAngle.value += 360 / 60
                }
                previousProgress.value = progress
                rotate(currentAngle.value, middle)
            },
            drawBlock = {
                drawLine(
                    strokeWidth = hourHandWidth,
                    cap = StrokeCap.Round,
                    color = hourHandColor,
                    start = middle,
                    end = hourHandHeight
                )
            }
        )

        withTransform(
            transformBlock = {
                rotate(degrees = 360 * progress, pivot = middle)
            },
            drawBlock = {
                drawLine(
                    strokeWidth = minuteHandWidth,
                    cap = StrokeCap.Round,
                    color = minuteHandColor,
                    start = middle,
                    end = minuteHandHeight
                )
            }
        )

        drawCircle(
            color = watchColor,
            radius = size.minDimension / 2,
            center = middle,
            style = clockBorderStroke
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DarkThemePreview")
@Composable
fun DefaultPreview() {
    ClockScreen()
}