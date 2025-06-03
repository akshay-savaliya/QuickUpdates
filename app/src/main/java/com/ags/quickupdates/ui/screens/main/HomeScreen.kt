package com.ags.quickupdates.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ags.quickupdates.navigation.Routes
import com.ags.quickupdates.ui.screens.auth.AuthViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Home Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.signOut()
            navController.navigate(Routes.AUTH) {
                popUpTo(Routes.HOME) { inclusive = true }
                launchSingleTop = true
            }
        }) {
            Text("Sign Out")
        }
    }
}