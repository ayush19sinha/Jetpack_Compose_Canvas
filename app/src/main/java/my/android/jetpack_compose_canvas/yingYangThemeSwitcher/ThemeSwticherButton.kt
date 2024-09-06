
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import my.android.jetpack_compose_canvas.yingYangThemeSwitcher.YinYang

@Composable
fun ThemeSwitcher(
    darkTheme: Boolean = false,
    size: Dp = 150.dp,
    shape: Shape = CircleShape,
    animationSpec: FiniteAnimationSpec<Float> = tween(durationMillis = 500),
    onClick: () -> Unit
) {
    var backgroundVisible by remember { mutableStateOf(false) }
    var yinYangVisible by remember { mutableStateOf(false) }

    val offset by animateFloatAsState(
        targetValue = if (darkTheme) 1f else 0f,
        animationSpec = animationSpec,
        label = "offset"
    )

    val rotation by animateFloatAsState(
        targetValue = if (darkTheme) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "rotation"
    )

    LaunchedEffect(Unit) {
        delay(800)
        backgroundVisible = true
        delay(500)
        yinYangVisible = true
    }

    Box {
        AnimatedVisibility(
            visible = backgroundVisible,
            enter = fadeIn()
        ) {
            Box(
                modifier = Modifier
                    .width(size * 2)
                    .height(size)
                    .clip(shape = shape)
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = shape
                    )
                    .clickable(onClick = onClick)
                    .background(if (darkTheme) Color.White else Color.Gray)
            ) {
                Box(
                    modifier = Modifier
                        .size(size)
                        .padding(4.dp)
                        .offset(x = size * offset)
                        .rotate(rotation),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(
                        visible = yinYangVisible,
                        enter = fadeIn()
                    ) {
                        YinYang()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ThemeSwitcherPreview() {
    ThemeSwitcher {}
}