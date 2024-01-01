package com.example.catfacts.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable function to display a custom ElevatedCard with specific styling.
 *
 * @param content The content to be displayed inside the elevated card.
 */
@Composable
fun CatElevatedCard(content: @Composable () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.surface),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
        ),

    ) {
        content()
    }
}
