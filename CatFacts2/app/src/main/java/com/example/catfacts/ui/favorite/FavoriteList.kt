package com.example.catfacts.ui.favorite

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.catfacts.data.Fact

/**
 * Composable function to display a list of favorite facts.
 *
 * @param factsList The list of [Fact] instances representing favorite facts to be displayed.
 * @param onRemoveCLicked The callback function triggered when the remove icon is clicked for a fact.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteList(factsList: List<Fact>, onRemoveCLicked: (Fact) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        itemsIndexed(
            items = factsList,
            key = { _, fact -> fact.content },
        ) { _, fact ->
            FavoriteCard(
                fact = fact,
                onRemoveCLicked = onRemoveCLicked,
                modifier = Modifier.animateItemPlacement(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = LinearOutSlowInEasing,
                    ),
                ),
            )
        }
    }
}
