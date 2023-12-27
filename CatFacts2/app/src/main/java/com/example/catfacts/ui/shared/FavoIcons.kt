package com.example.catfacts.ui.shared

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.catfacts.R

@Composable
fun removeIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.Default.Delete,
            contentDescription = stringResource(R.string.icon_delete_description),
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
        )
    }
}

@Composable
fun unFavoriteIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.Default.Favorite,
            contentDescription = stringResource(R.string.icon_delete_description),
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
        )
    }
}

@Composable
fun favoriteIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.Default.FavoriteBorder,
            contentDescription = stringResource(R.string.icon_favorite_description),
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
        )
    }
}


