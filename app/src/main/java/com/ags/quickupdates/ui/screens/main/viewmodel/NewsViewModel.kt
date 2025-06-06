package com.ags.quickupdates.ui.screens.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ags.quickupdates.ui.screens.main.NewsPagingSource
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsApiClient: NewsApiClient
) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchEverythingWithQuery(query: String = "GENERAL") {
        _isLoading.value = true
        val request = EverythingRequest.Builder().language("en").q(query).build()

        newsApiClient.getEverything(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
                }
                _isLoading.value = false
            }

            override fun onFailure(throwable: Throwable?) {
                Log.i("NewsAPI Response", "Error: ${throwable?.message}")
                _isLoading.value = false
            }
        })
    }

    fun getPagedNews(category: String = "GENERAL"): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { NewsPagingSource(category, newsApiClient) }
        ).flow.cachedIn(viewModelScope)
    }
}