package com.ags.quickupdates.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.ags.quickupdates.R
import com.ags.quickupdates.navigation.Routes
import com.ags.quickupdates.ui.screens.auth.AuthViewModel
import com.ags.quickupdates.util.AppPreferences
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(navController: NavHostController, viewModel: AuthViewModel) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val user = FirebaseAuth.getInstance().currentUser
    val isDarkTheme by AppPreferences.getThemePreference(context).collectAsState(initial = false)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile
        if (user != null) {
            AsyncImage(
                model = user.photoUrl,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = user.displayName ?: "User", fontWeight = FontWeight.Bold)
            Text(text = user.email ?: "")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Theme Toggle (basic)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Select Theme", modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    scope.launch {
                        AppPreferences.saveThemePreference(context, !isDarkTheme)
                    }
                }
            ) {
                Icon(
                    painter = if (isDarkTheme) {
                        painterResource(id = R.drawable.outline_light_mode_24)
                    } else {
                        painterResource(id = R.drawable.outline_dark_mode_24)
                    },
                    contentDescription = if (isDarkTheme) "Switch to Light Mode" else "Switch to Dark Mode"

                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Logout
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            onClick = {
                viewModel.signOut()
                navController.navigate(Routes.AUTH) {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            }
        ) {
            Text("Logout", color = Color.White)
        }
    }
}