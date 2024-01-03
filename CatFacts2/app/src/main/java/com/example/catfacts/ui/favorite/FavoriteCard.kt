package com.example.catfacts.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.catfacts.data.Fact
import com.example.catfacts.ui.shared.CatElevatedCard
import com.example.catfacts.ui.shared.RemoveIcon

/**
 * Composable function to display a card for a favorite fact.
 *
 * @param fact The [Fact] instance representing the favorite fact to be displayed.
 * @param onRemoveCLicked The callback function triggered when the remove icon is clicked.
 */
@Composable
fun FavoriteCard(fact: Fact, onRemoveCLicked: (Fact) -> Unit, modifier: Modifier = Modifier) {
    CatElevatedCard(modifier = modifier) {
        Text(
            text = fact.content,
            modifier = Modifier
                .padding(10.dp),
        )

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth(),
        ) {
            RemoveIcon(modifier = Modifier.testTag(fact.content)) { onRemoveCLicked(fact) }
        }
    }
}
