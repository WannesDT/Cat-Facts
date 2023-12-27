package com.example.catfacts.ui.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.catfacts.data.Fact

@Composable
fun FavoriteList(factsList: List<Fact>, onRemoveCLicked: (Fact) -> Unit) {
    LazyColumn() {
        items(factsList) { fact ->
            FavoriteCard(fact = fact, onRemoveCLicked = onRemoveCLicked)
        }
    }
}
