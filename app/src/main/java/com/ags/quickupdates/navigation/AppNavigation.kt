package com.ags.quickupdates.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ags.quickupdates.ui.screens.auth.AuthScreen
import com.ags.quickupdates.ui.screens.auth.AuthViewModel
import com.ags.quickupdates.ui.screens.main.HomeScreen
import com.ags.quickupdates.ui.screens.main.NewsArticleScreen
import com.ags.quickupdates.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val viewModel: AuthViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {

        composable(Routes.SPLASH) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }

        composable(Routes.AUTH) {
            AuthScreen(navController = navController, viewModel = viewModel)
        }

        composable(Routes.HOME) {
            HomeScreen(modifier = modifier, navController = navController)
        }

        composable(
            route = Routes.ARTICLE,
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) {
            val url = it.arguments?.getString("url") ?: ""
            NewsArticleScreen(url = Uri.decode(url))
        }

    }
}