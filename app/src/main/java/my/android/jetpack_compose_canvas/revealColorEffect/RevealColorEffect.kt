package my.android.jetpack_compose_canvas.revealColorEffect

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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import my.android.jetpack_compose_canvas.R

@Composable
fun RevealColorEffect(
    @DrawableRes imageRes: Int,
    revealRadius: Float = 250f,
) {
    val image = ImageBitmap.imageResource(id = imageRes)
    var touchPosition by remember { mutableStateOf(Offset.Zero) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    touchPosition = change.position
                }
            }.onSizeChanged {
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

        drawImage(
            image = image,
            dstSize = imageSize,
            colorFilter = ColorFilter.tint(color = Color.Black, blendMode = BlendMode.Saturation)
        )

        drawCircle(
            color = Color.Transparent,
            radius = revealRadius,
            center = touchPosition,
            blendMode = BlendMode.Clear
        )
        drawContext.canvas.restore()
    }
}

@Composable
fun RevealColorScreen() {
    RevealColorEffect(imageRes = R.drawable.anime_street)
}