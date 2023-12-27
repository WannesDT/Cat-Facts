package com.example.catfacts.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catfacts.R
import com.example.catfacts.ui.shared.removeIcon
import com.example.catfacts.ui.states.FavoriteApiState
import com.example.catfacts.viewmodels.FavoriteFactViewModel

@Composable
fun FavoritePage() {
    val favoriteFactViewModel: FavoriteFactViewModel = viewModel(factory = FavoriteFactViewModel.Factory)
    val uiState = favoriteFactViewModel.apiState

    Text(
        stringResource(id = R.string.saved_facts),
        style = MaterialTheme.typography.titleMedium,
    )
    when (uiState) {
        is FavoriteApiState.Loading -> {
            Text(text = "Loading")
        }
        is FavoriteApiState.Success -> {
            val factsList = favoriteFactViewModel.uiListState.collectAsState().value

            if (factsList.isNotEmpty()) {
                LazyColumn() {
                    items(factsList) { fact ->
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
                            Text(
                                text = fact.content,
                                modifier = Modifier
                                    .padding(10.dp),
                            )

                            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                                removeIcon { favoriteFactViewModel.removeFavorite(fact) }
                            }
                        }
                    }
                }
            }
        }

        else -> {
            Button(onClick = { favoriteFactViewModel.getFavoriteFacts() }) {
                Text(text = "Reload")
            }
        }
    }
}
