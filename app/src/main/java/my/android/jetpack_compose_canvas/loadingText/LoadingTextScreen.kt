package my.android.jetpack_compose_canvas.loadingText

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInSine
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import my.android.jetpack_compose_canvas.ui.theme.Background
import my.android.jetpack_compose_canvas.ui.theme.LightOrange
import my.android.jetpack_compose_canvas.ui.theme.Pink
import my.android.jetpack_compose_canvas.ui.theme.Purple
import my.android.jetpack_compose_canvas.ui.theme.Yellow

@Composable
fun LoadingTextScreen() {
    val scope = rememberCoroutineScope()
    val progressAnimation = remember { Animatable(0f) }
    var isPlaying by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition()
    val yOffset = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = LinearEasing
            )
        )
    )
    val forwardAnimationSpec = remember {
        tween<Float>(
            durationMillis = 10000,
            easing = LinearEasing
        )
    }
    val resetAnimationSpec = remember {
        tween<Float>(
            durationMillis = 1000,
            easing = EaseInSine
        )
    }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            while (true) {
                progressAnimation.animateTo(1f, forwardAnimationSpec)
                progressAnimation.snapTo(0f)
            }
        }
    }

    fun reset() {
        isPlaying = false
        scope.launch {
            progressAnimation.animateTo(0f, resetAnimationSpec)
        }
    }

    fun togglePlay() {
        isPlaying = !isPlaying
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Content(
            modifier = Modifier
                .align(Alignment.Center)
                .loadingRevealAnimation(
                    progress = progressAnimation.asState(),
                    yOffset = yOffset
                )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(24.dp)
                .safeContentPadding()
                .align(Alignment.BottomCenter)
        ) {
            Button(onClick = ::reset) {
                Text("Reset")
            }
            Button(onClick = ::togglePlay) {
                AnimatedContent(
                    targetState = isPlaying
                ) { isPlaying ->
                    Text(if (isPlaying) "Pause" else "Play")
                }
            }
        }
    }
}

private fun Modifier.loadingRevealAnimation(
    progress: State<Float>,
    yOffset: State<Float>,
    wavesCount: Int = 2,
    amplitudeProvider: (totalSize: Size) -> Float = { it.minDimension * 0.1f }
): Modifier = this
    .graphicsLayer(
        compositingStrategy = CompositingStrategy.Offscreen
    )
    .drawWithCache {
        val height = size.height
        val waveLength = height / wavesCount
        val nextPointOffset = waveLength / 2f
        val controlPointOffset = nextPointOffset / 2f
        val amplitude = amplitudeProvider(size)
        val wavePath = Path()

        onDrawWithContent {
            drawContent()

            val wavesStartX = (size.width + 2 * amplitude) * progress.value - amplitude

            wavePath.reset()
            wavePath.relativeLineTo(wavesStartX, -waveLength)
            wavePath.relativeLineTo(0f, waveLength * yOffset.value)

            repeat((wavesCount + 1) * 2) { i ->
                val direction = if (i and 1 == 0) -1 else 1

                wavePath.relativeQuadraticTo(
                    dx1 = direction * amplitude,
                    dy1 = controlPointOffset,
                    dx2 = 0f,
                    dy2 = nextPointOffset
                )
            }

            wavePath.lineTo(0f, height)
            wavePath.close()

            drawPath(
                path = wavePath,
                brush = Gradient,
                blendMode = BlendMode.SrcAtop
            )
        }
    }


private val Gradient = Brush.linearGradient(
    colorStops = arrayOf(
        0.0f to Pink,
        0.4f to Purple,
        0.7f to LightOrange,
        1.0f to Yellow
    )
)

@Composable
private fun Content(modifier: Modifier = Modifier) {
    Text(
        text = "Loading\nPlease\nWait.",
        modifier = modifier,
        fontSize = 100.sp,
        lineHeight = 100.sp,
        fontWeight = FontWeight.Black,
        color = Background
    )
}
