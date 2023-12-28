package com.example.catfacts.ui.favorite

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
import com.example.catfacts.ui.shared.removeIcon

@Composable
fun FavoriteCard(fact: Fact, onRemoveCLicked: (Fact) -> Unit) {
    CatElevatedCard {
        Text(
            text = fact.content,
            modifier = Modifier
                .padding(10.dp),
        )

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth(),
        ) {
            removeIcon { onRemoveCLicked(fact) }
        }
    }
}
