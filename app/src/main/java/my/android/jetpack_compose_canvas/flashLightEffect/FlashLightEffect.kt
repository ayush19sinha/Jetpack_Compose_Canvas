
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import my.android.jetpack_compose_canvas.R

@Composable
fun FlashLightEffect(
    @DrawableRes imageRes: Int,
    flashLightRadius: Float = 450f,
) {
    val image = ImageBitmap.imageResource(id = imageRes)
    var touchPosition by remember { mutableStateOf(Offset.Zero) }
    val darkOverlay = Color.Black.copy(alpha = .8f)

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    touchPosition = change.position
                }
            }
                .onSizeChanged {
                touchPosition = Offset(it.width / 2f, it.height / 2f)
            }
    ) {
        val imageSize = IntSize(width = size.width.toInt(), height = size.height.toInt())

        drawImage(
            image = image,
            dstSize = imageSize
        )

        drawContext.canvas.saveLayer(
            Rect(Offset.Zero, size),
            paint = Paint()
        )

        drawRect(color = darkOverlay)

        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                shader = RadialGradientShader(
                    colors = listOf(Color.White, Color.Transparent),
                    center = touchPosition,
                    radius = flashLightRadius
                )
                blendMode = BlendMode.DstOut
            }

            canvas.drawCircle(
                center = touchPosition,
                radius = flashLightRadius,
                paint = paint
            )
        }
        drawContext.canvas.restore()
    }
}

@Composable
fun FlashLightScreen() {
    FlashLightEffect(imageRes = R.drawable.anime_street)
}
