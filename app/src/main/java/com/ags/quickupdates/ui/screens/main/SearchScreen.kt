package com.ags.quickupdates.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ags.quickupdates.ui.components.ArticleItem
import com.ags.quickupdates.ui.screens.main.viewmodel.NewsViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    var query by remember { mutableStateOf("") }
    val articles = newsViewModel.articles.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }

            TextField(
                value = query,
                onValueChange = {
                    query = it
                    if (query.length >= 3) {
                        newsViewModel.fetchEverythingWithQuery(query)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search news...") },
                singleLine = true
            )
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(articles.value) { article ->
                ArticleItem(article, navController)
            }
        }
    }
}