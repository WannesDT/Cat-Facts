package com.example.catfacts.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.catfacts.data.Fact

/**
 * Composable function to display a list of random facts in a LazyColumn.
 *
 * @param listOfFacts The list of [Fact] instances representing random facts to be displayed.
 * @param onFavoriteClicked The callback function triggered when the favorite/unfavorite icon is clicked.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RandomFactList(listOfFacts: List<Fact>, onFavoriteClicked: (Fact) -> Unit, listState: LazyListState = rememberLazyListState()) {
    LazyColumn(state = listState, modifier = Modifier.fillMaxHeight(0.85f).animateContentSize()) {
        itemsIndexed(
            items = listOfFacts,
            key = { _, fact -> fact.content },
        ) { _, fact ->

            FactCard(
                fact = fact,
                onFavoriteClicked = onFavoriteClicked,
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
