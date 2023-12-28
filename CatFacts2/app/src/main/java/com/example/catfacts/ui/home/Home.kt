package com.example.catfacts.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catfacts.R
import com.example.catfacts.ui.shared.favoriteIcon
import com.example.catfacts.ui.shared.unFavoriteIcon
import com.example.catfacts.ui.states.FactApiState
import com.example.catfacts.viewmodels.FactViewModel

@Composable
fun Home() {
    val orientation = LocalConfiguration.current.orientation

    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                homeScreenContent()
            }
        }

        else -> {
            Column {
                homeScreenContent()
            }
        }
    }
}

@Composable
private fun homeScreenContent() {
    val factViewModel: FactViewModel = viewModel(factory = FactViewModel.Factory)
    val uiState = factViewModel.apiState
    val orientation = LocalConfiguration.current.orientation

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth(if (orientation == Configuration.ORIENTATION_LANDSCAPE) 0.5f else 1f)
            .fillMaxHeight(if (orientation == Configuration.ORIENTATION_LANDSCAPE) 1f else 0.5f)
            .padding(top = 5.dp),

    ) {
        Image(
            painter = painterResource(id = R.drawable.olli_the_polite_cat),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
        )
    }
    Column(modifier = Modifier.padding(end = 5.dp)) {
        when (uiState) {
            is FactApiState.Loading -> {
                Text(text = stringResource(id = R.string.loading))
            }

            is FactApiState.Success -> {
                val fact = uiState.fact

                Text(
                    text = fact.content,
                    modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()),
                )

                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    if (factViewModel.isFavoriteState) {
                        unFavoriteIcon {
                            factViewModel.removeFavorite(fact)
                        }
                    } else {
                        favoriteIcon {
                            factViewModel.addFavorite(fact)
                        }
                    }
                    Button(onClick = { factViewModel.getApiFact() }) {
                        Text(text = stringResource(id = R.string.get_fact))
                    }
                }
            }

            else -> {
                Text(
                    text = stringResource(id = R.string.fact_no_internet),
                    modifier = Modifier.weight(1f),
                )
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { factViewModel.getApiFact() }) {
                        Text(text = stringResource(id = R.string.get_fact))
                    }
                }
            }
        }
    }
}
