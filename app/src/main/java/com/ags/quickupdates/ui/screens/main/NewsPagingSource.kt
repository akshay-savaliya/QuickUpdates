package com.ags.quickupdates.ui.screens.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NewsPagingSource(
    private val category: String,
    private val newsApiClient: NewsApiClient
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(20)

        return try {
            val request = TopHeadlinesRequest.Builder()
                .category(category)
                .language("en")
                .pageSize(pageSize)
                .page(page)
                .build()

            val articles = suspendCoroutine<List<Article>> { cont ->
                newsApiClient.getTopHeadlines(
                    request,
                    object : NewsApiClient.ArticlesResponseCallback {
                        override fun onSuccess(response: ArticleResponse?) {
                            cont.resume(response?.articles ?: emptyList())
                        }

                        override fun onFailure(throwable: Throwable?) {
                            cont.resumeWithException(throwable ?: Exception("Unknown error"))
                        }
                    })
            }

            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (articles.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}