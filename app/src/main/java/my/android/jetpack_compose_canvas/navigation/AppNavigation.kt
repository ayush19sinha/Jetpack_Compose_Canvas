package my.android.jetpack_compose_canvas.navigation

import FlashLightScreen
import GrayScaleScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import my.android.jetpack_compose_canvas.home.HomeScreen
import my.android.jetpack_compose_canvas.loadingText.LoadingTextScreen
import my.android.jetpack_compose_canvas.revealColorEffect.RevealColorScreen
import my.android.jetpack_compose_canvas.yingYangThemeSwitcher.ThemeSwitcherScreen
import my.android.jetpack_compose_canvas.yingYangThemeSwitcher.viewModel.ThemeViewModel

@Composable
fun AppNavigation(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeNav
    ) {
        composable<HomeNav> {
            HomeScreen(
                onThemeSwitcherClick = { navController.navigate(ThemeSwitcherNav) },
                onFlashLightClick = { navController.navigate(FlashLightNav) },
                onRevealColorClick = { navController.navigate(RevealColorNav) },
                onGrayScaleClick = { navController.navigate(GrayScaleNav) },
                onLoadingTextClick = { navController.navigate(LoadingTextNav) }
            )
        }
        composable<ThemeSwitcherNav> {
            ThemeSwitcherScreen(themeViewModel = themeViewModel)
        }
        composable<FlashLightNav> {
            FlashLightScreen()
        }
        composable<RevealColorNav> {
            RevealColorScreen()
        }
        composable<GrayScaleNav> {
            GrayScaleScreen()
        }
        composable<LoadingTextNav> {
            LoadingTextScreen()
        }
    }
}
