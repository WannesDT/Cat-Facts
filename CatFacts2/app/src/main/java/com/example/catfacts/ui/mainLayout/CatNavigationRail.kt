package com.example.catfacts.ui.mainLayout

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.catfacts.R

/**
 * Composable function to display a custom NavigationRail with icons for home and favorite pages.
 *
 * @param showHomePage The callback function triggered when the home icon is clicked.
 * @param showFavoritePage The callback function triggered when the favorite icon is clicked.
 */
@Composable
fun CatNavigationRail(showHomePage: () -> Unit, showFavoritePage: () -> Unit) {
    NavigationRail {
        NavigationRailItem(
            selected = false,
            onClick = showHomePage,
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = stringResource(R.string.appbar_icon_home_description),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                )
            },
        )

        NavigationRailItem(
            selected = false,
            onClick = showFavoritePage,
            icon = {
                Icon(
                    Icons.Default.Star,
                    contentDescription = stringResource(R.string.appbar_icon_favorite_description),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                )
            },
        )
    }
}
