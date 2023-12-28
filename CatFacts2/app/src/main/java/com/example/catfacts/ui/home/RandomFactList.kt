package com.example.catfacts.ui.home

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.catfacts.data.Fact

@Composable
fun RandomFactList(listOfFacts: List<Fact>, onFavoriteClicked: (Fact) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxHeight(0.85f)) {
        items(listOfFacts) { item ->
            FactCard(fact = item, onFavoriteClicked = onFavoriteClicked)
        }
    }
}
