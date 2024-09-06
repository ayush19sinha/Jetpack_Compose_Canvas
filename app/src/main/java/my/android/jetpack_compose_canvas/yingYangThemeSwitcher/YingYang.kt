package my.android.jetpack_compose_canvas.yingYangThemeSwitcher


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun YinYang(size: Int = 200) {

    Canvas(modifier = Modifier.size(size.dp)) {

        val center = Offset(this.size.width / 2, this.size.height / 2)
        val radius = this.size.width / 2

        drawArc(
            color = Color.Black,
            startAngle = 270f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(radius * 2, radius * 2),
            topLeft = Offset.Zero
        )

        drawCircle(
            color = Color.White,
            radius = radius / 2,
            center = Offset(center.x, center.y - radius / 2)
        )

        drawArc(
            color = Color.White,
            startAngle = 90f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(radius * 2, radius * 2),
            topLeft = Offset.Zero
        )

        drawCircle(
            color = Color.Black,
            radius = radius / 2,
            center = Offset(center.x, center.y + radius / 2)
        )

        drawCircle(
            color = Color.Black,
            radius = radius / 6,
            center = Offset(center.x, center.y - radius / 2)
        )

        drawCircle(
            color = Color.White,
            radius = radius / 6,
            center = Offset(center.x, center.y + radius / 2)
        )
    }
}

@Preview
@Composable
private fun YinYangPreview() {
    YinYang()
}
