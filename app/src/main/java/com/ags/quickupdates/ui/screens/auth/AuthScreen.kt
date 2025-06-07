package com.ags.quickupdates.ui.screens.auth

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ags.quickupdates.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ags.quickupdates.navigation.Routes
import com.ags.quickupdates.ui.components.GoogleSignInButton
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(navController: NavHostController, viewModel: AuthViewModel) {

    val context = LocalContext.current
    val activity = context as? Activity
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
                .systemBarsPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Quick Updates",
                style = MaterialTheme.typography.headlineLarge.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Stay informed, instantly",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Image(
                painter = painterResource(id = R.drawable.auth_banner),
                contentDescription = "Auth banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            Text(
                text = "Fast. Accurate. Reliable.",
                style = MaterialTheme.typography.headlineLarge.copy(
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Built for speed. Designed for truth.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            GoogleSignInButton(
                isLoading = isLoading,
                onClick = {
                    isLoading = true
                    scope.launch {
                        if (activity != null && viewModel.signIn(activity)) {
                            isLoading = false
                            navController.navigate(Routes.HOME) {
                                popUpTo(Routes.AUTH) { inclusive = true }
                                launchSingleTop = true
                            }
                        } else {
                            isLoading = false
                            Toast.makeText(context, "Sign in failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }
    }
}