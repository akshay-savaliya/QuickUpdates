package com.ags.quickupdates.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ags.quickupdates.ui.screens.main.HomeScreen
import com.ags.quickupdates.ui.splash.SplashScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {

        composable(Routes.SPLASH) {
            SplashScreen(navController = navController)
        }

        composable(Routes.HOME) {
            HomeScreen(modifier = modifier)
        }
    }
}