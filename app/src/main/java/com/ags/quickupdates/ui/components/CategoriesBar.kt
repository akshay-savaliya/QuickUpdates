package com.ags.quickupdates.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesBar(onCategorySelected: (String) -> Unit) {

    val categories =
        listOf(
            "GENERAL",
            "BUSINESS",
            "ENTERTAINMENT",
            "HEALTH",
            "SCIENCE",
            "SPORTS",
            "TECHNOLOGY"
        )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        categories.forEach { category ->
            Button(
                onClick = { onCategorySelected(category) },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = category)
            }
        }
    }
}