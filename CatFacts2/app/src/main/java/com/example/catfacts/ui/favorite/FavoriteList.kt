package com.example.catfacts.ui.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.catfacts.data.Fact

/**
 * Composable function to display a list of favorite facts.
 *
 * @param factsList The list of [Fact] instances representing favorite facts to be displayed.
 * @param onRemoveCLicked The callback function triggered when the remove icon is clicked for a fact.
 */
@Composable
fun FavoriteList(factsList: List<Fact>, onRemoveCLicked: (Fact) -> Unit) {
    LazyColumn() {
        items(factsList) { fact ->
            FavoriteCard(fact = fact, onRemoveCLicked = onRemoveCLicked)
        }
    }
}
