package com.ags.quickupdates.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ags.quickupdates.navigation.Routes
import com.ags.quickupdates.ui.screens.main.viewmodel.NewsViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    newsViewModel: NewsViewModel = hiltViewModel()
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Settings Icon (Left)
            IconButton(onClick = { navController.navigate(Routes.SETTINGS) }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }

            Text(
                text = "Quick Updates",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = FontFamily.Serif
            )

            // Search Icon (Right)
            IconButton(onClick = { navController.navigate(Routes.SEARCH) }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
        NewsScreen(
            navController = navController,
            newsViewModel = newsViewModel
        )
    }
}