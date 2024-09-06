package my.android.jetpack_compose_canvas.yingYangThemeSwitcher

import ThemeSwitcher
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import my.android.jetpack_compose_canvas.yingYangThemeSwitcher.viewModel.ThemeViewModel

@Composable
fun Home(themeViewModel: ThemeViewModel, modifier: Modifier = Modifier) {
    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState(initial = false)
    var textVisibility by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1800)
        textVisibility = true
    }

    Box(modifier = modifier.fillMaxSize()) {
        AnimatedTitleText(
            isDarkTheme = isDarkTheme,
            textVisibility = textVisibility
        )

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ThemeSwitcher(
                darkTheme = isDarkTheme,
                size = 50.dp,
                onClick = themeViewModel::toggleTheme
            )
        }
    }
}

@Composable
private fun AnimatedTitleText(isDarkTheme: Boolean, textVisibility: Boolean) {
    AnimatedVisibility(
        visible = textVisibility,
        enter = slideIn(
            animationSpec = tween(500),
            initialOffset = { fullSize -> IntOffset(x = 0, y = fullSize.height) }
        )
    ) {
        Column(modifier = Modifier.padding(top = 100.dp, start = 40.dp)) {
            val textColor = if (isDarkTheme) Color.White else Color.Black
            Text(
                text = "Theme Switcher",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = textColor
            )
            Text(
                text = "by Ayush",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = textColor,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    val themeViewModel = ThemeViewModel()
    Home(themeViewModel = themeViewModel)
}