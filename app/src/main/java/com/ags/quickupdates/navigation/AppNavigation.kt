package com.ags.quickupdates.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ags.quickupdates.ui.screens.auth.AuthScreen
import com.ags.quickupdates.ui.screens.auth.AuthViewModel
import com.ags.quickupdates.ui.screens.main.HomeScreen
import com.ags.quickupdates.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val context = LocalContext.current
    val viewModel = remember { AuthViewModel(context) }

    NavHost(navController = navController, startDestination = Routes.SPLASH) {

        composable(Routes.SPLASH) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }

        composable(Routes.AUTH) {
            AuthScreen(navController = navController, viewModel = viewModel)
        }

        composable(Routes.HOME) {
            HomeScreen(modifier = modifier, navController = navController, viewModel = viewModel)
        }
    }
}