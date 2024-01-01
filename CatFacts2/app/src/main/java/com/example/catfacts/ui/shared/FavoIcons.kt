package com.example.catfacts.ui.shared

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.catfacts.R

/**
 * Composable function to display an IconButton with a delete icon.
 *
 * @param onClick The callback function triggered when the delete icon is clicked.
 */
@Composable
fun RemoveIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.Default.Delete,
            contentDescription = stringResource(R.string.icon_delete_description),
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
        )
    }
}

/**
 * Composable function to display an IconButton with an unfavorite icon.
 *
 * @param onClick The callback function triggered when the unfavorite icon is clicked.
 */
@Composable
fun UnFavoriteIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.Default.Favorite,
            contentDescription = stringResource(R.string.icon_delete_description),
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
        )
    }
}

/**
 * Composable function to display an IconButton with a favorite icon.
 *
 * @param onClick The callback function triggered when the favorite icon is clicked.
 */
@Composable
fun FavoriteIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.Default.FavoriteBorder,
            contentDescription = stringResource(R.string.icon_favorite_description),
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
        )
    }
}


