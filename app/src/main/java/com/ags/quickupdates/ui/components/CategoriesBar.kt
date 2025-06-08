package com.ags.quickupdates.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesBar(
    onCategorySelected: (String) -> Unit,
    selectedCategory: String = "GENERAL"
) {
    val categories = listOf(
        "GENERAL", "BUSINESS", "ENTERTAINMENT",
        "HEALTH", "SCIENCE", "SPORTS", "TECHNOLOGY"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        categories.forEach { category ->
            val isSelected = selectedCategory == category
            Button(
                onClick = { onCategorySelected(category) },
                modifier = Modifier
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = if (isSelected)
                        MaterialTheme.colorScheme.onPrimary
                    else
                        MaterialTheme.colorScheme.onSurface
                ),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(text = category.lowercase().replaceFirstChar { it.uppercase() })
            }
        }
    }
}