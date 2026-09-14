package my.android.jetpack_compose_canvas.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.android.jetpack_compose_canvas.ui.theme.Background
import my.android.jetpack_compose_canvas.ui.theme.PurpleGrey40

@Composable
fun HomeScreen(
    onThemeSwitcherClick: () -> Unit,
    onFlashLightClick: () -> Unit,
    onRevealColorClick: () -> Unit,
    onGrayScaleClick: () -> Unit,
    onLoadingTextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Background,
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Canvas Animations",
                fontSize = 22.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Choose an effect to explore",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            AnimationButton("Loading Text", onLoadingTextClick)
            AnimationButton("Theme Switcher", onThemeSwitcherClick)
            AnimationButton("Flashlight", onFlashLightClick)
            AnimationButton("Reveal Color", onRevealColorClick)
            AnimationButton("Grayscale", onGrayScaleClick)
        }
    }
}

@Composable
private fun AnimationButton(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PurpleGrey40,
            contentColor = Color.White
        )
    ) {
        Text(
            text = title,
            lineHeight = 24.sp,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}