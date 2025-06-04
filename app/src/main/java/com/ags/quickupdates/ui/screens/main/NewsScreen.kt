package com.ags.quickupdates.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ags.quickupdates.ui.components.ArticleItem
import com.ags.quickupdates.ui.components.CategoriesBar
import com.ags.quickupdates.ui.screens.main.viewmodel.NewsViewModel

@Composable
fun NewsScreen(newsViewModel: NewsViewModel, navController: NavHostController) {

    val articles = newsViewModel.articles.observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        CategoriesBar(newsViewModel)

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(articles.value) { article ->
                ArticleItem(article, navController)
            }
        }
    }
}