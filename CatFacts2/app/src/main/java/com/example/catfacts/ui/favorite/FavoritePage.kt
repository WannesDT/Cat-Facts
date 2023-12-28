package com.example.catfacts.ui.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catfacts.R
import com.example.catfacts.ui.states.FavoriteApiState
import com.example.catfacts.viewmodels.FavoriteFactViewModel

@Composable
fun FavoritePage() {
    val favoriteFactViewModel: FavoriteFactViewModel = viewModel(factory = FavoriteFactViewModel.Factory)
    val uiState = favoriteFactViewModel.apiState

    Column {
        Text(
            stringResource(id = R.string.saved_facts),
            style = MaterialTheme.typography.titleLarge,
        )
        when (uiState) {
            is FavoriteApiState.Loading -> {
                Text(text = stringResource(id = R.string.loading))
            }

            is FavoriteApiState.Success -> {
                val factsList = favoriteFactViewModel.uiListState.collectAsState().value

                if (factsList.isNotEmpty()) {
                    FavoriteList(factsList) { favoriteFactViewModel.removeFavorite(it) }
                }
            }

            else -> {
                Button(onClick = { favoriteFactViewModel.getFavoriteFacts() }) {
                    Text(text = "Reload")
                }
            }
        }
    }
}
