package com.example.catfacts.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.catfacts.data.Fact
import com.example.catfacts.ui.shared.CatElevatedCard
import com.example.catfacts.ui.shared.FavoriteIcon
import com.example.catfacts.ui.shared.UnFavoriteIcon

/**
 * Composable function to display a card for a fact.
 *
 * @param fact The [Fact] instance representing the fact to be displayed.
 * @param onFavoriteClicked The callback function triggered when the favorite/unfavorite icon is clicked.
 */
@Composable
fun FactCard(fact: Fact, onFavoriteClicked: (Fact) -> Unit, modifier: Modifier = Modifier) {
    CatElevatedCard(
        modifier = modifier,
    ) {
        Text(
            text = fact.content,
            modifier = Modifier
                .padding(10.dp),
        )

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (fact.isFavorite)UnFavoriteIcon { onFavoriteClicked(fact) } else FavoriteIcon { onFavoriteClicked(fact) }
        }
    }
}
