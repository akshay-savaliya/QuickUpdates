package com.ags.quickupdates.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ags.quickupdates.ui.screens.auth.AuthViewModel
import com.ags.quickupdates.ui.screens.main.viewmodel.NewsViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val newsViewModel: NewsViewModel = viewModel()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Quick Updates",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            fontFamily = FontFamily.Serif
        )
        NewsScreen(newsViewModel, navController)
    }
}