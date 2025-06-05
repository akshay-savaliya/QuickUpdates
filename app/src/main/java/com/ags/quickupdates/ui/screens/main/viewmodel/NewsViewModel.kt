package com.ags.quickupdates.ui.screens.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ags.quickupdates.ui.screens.main.NewsPagingSource
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsApiClient: NewsApiClient
) : ViewModel() {

    fun getPagedNews(category: String = "GENERAL"): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { NewsPagingSource(category, newsApiClient) }
        ).flow.cachedIn(viewModelScope)
    }
}