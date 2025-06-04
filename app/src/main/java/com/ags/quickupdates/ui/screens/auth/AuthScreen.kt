package com.ags.quickupdates.ui.screens.auth

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ags.quickupdates.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(navController: NavHostController, viewModel: AuthViewModel) {

    val context = LocalContext.current
    val activity = context as? Activity
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Auth Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                scope.launch {
                    if (activity != null && viewModel.signIn(activity)) {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.AUTH) { inclusive = true }
                            launchSingleTop = true
                        }
                    } else {
                        Toast.makeText(context, "Sign in failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        ) {
            Text(text = "Sign in with Google")
        }
    }
}