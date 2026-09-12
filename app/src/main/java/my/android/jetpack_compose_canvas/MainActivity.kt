package my.android.jetpack_compose_canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import my.android.jetpack_compose_canvas.navigation.AppNavigation
import my.android.jetpack_compose_canvas.ui.theme.CanvasTheme
import my.android.jetpack_compose_canvas.yingYangThemeSwitcher.viewModel.ThemeViewModel

class MainActivity : ComponentActivity() {

    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasTheme {
                AppNavigation(themeViewModel)
            }
        }
    }
}