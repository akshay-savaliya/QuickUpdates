package com.ags.quickupdates.ui.screens.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ags.quickupdates.ui.components.ArticleItem
import com.ags.quickupdates.ui.components.CategoriesBar
import com.ags.quickupdates.ui.screens.main.viewmodel.NewsViewModel
import com.ags.quickupdates.ui.shimmer.ShimmerArticleItemCard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsScreen(newsViewModel: NewsViewModel, navController: NavHostController) {

    val category = remember { mutableStateOf("GENERAL") }
    val articles = newsViewModel.getPagedNews(category.value).collectAsLazyPagingItems()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        CategoriesBar(
            onCategorySelected = {
                category.value = it
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            // 🔁 Show shimmer placeholders while loading the first page
            if (articles.loadState.refresh is LoadState.Loading) {
                items(10) { ShimmerArticleItemCard() }
            } else {
                items(count = articles.itemCount) { index ->
                    val article = articles[index]
                    if (article != null) {
                        ArticleItem(article, navController)
                    }
                }
            }

            when (articles.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                        }
                    }
                }

                is LoadState.Error -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Failed to load more news",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { articles.retry() }) {
                                Text("Retry")
                            }
                        }
                    }
                }

                else -> Unit
            }
        }
    }
}