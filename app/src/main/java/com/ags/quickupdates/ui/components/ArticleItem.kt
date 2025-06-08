package com.ags.quickupdates.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.ags.quickupdates.R
import com.ags.quickupdates.navigation.articleRoute
import com.ags.quickupdates.ui.shimmer.ShimmerBox
import com.ags.quickupdates.util.Common.formatDate
import com.kwabenaberko.newsapilib.models.Article

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleItem(article: Article, navController: NavHostController) {

    var isImageLoading by remember { mutableStateOf(true) }

    val imageAlpha by animateFloatAsState(
        targetValue = if (isImageLoading) 0f else 1f,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 100,
            easing = androidx.compose.animation.core.FastOutSlowInEasing
        ),
        label = "ImageFadeIn"
    )

    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = { article.url.let { navController.navigate(articleRoute(it)) } }
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            AsyncImage(
                model = article.urlToImage ?: R.drawable.outline_broken_image_24,
                contentDescription = "Article Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .alpha(imageAlpha),
                onSuccess = { isImageLoading = false },
                onError = { isImageLoading = false },
                onLoading = { isImageLoading = true },
                contentScale = ContentScale.Crop
            )
            if (isImageLoading) {
                ShimmerBox()
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = article.source.name,
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
                    )

                    article.publishedAt?.let {
                        Text(
                            text = formatDate(it),
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                        )
                    }
                }
            }
        }
    }
}