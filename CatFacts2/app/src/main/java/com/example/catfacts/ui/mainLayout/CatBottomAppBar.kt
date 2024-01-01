package com.example.catfacts.ui.mainLayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.catfacts.R

/**
 * Composable function to display a custom BottomAppBar with icons for home and favorite pages.
 *
 * @param showHomePage The callback function triggered when the home icon is clicked.
 * @param showFavoritePage The callback function triggered when the favorite icon is clicked.
 */
@Composable
fun CatBottomAppBar(showHomePage: () -> Unit, showFavoritePage: () -> Unit) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            IconButton(onClick = showHomePage) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = stringResource(R.string.appbar_icon_home_description),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                )
            }

            IconButton(onClick = showFavoritePage) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = stringResource(R.string.appbar_icon_favorite_description),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                )
            }
        }
    }
}
