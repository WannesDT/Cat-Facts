package com.example.catfacts.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.catfacts.R
import com.example.catfacts.ui.favorite.FavoritePage
import com.example.catfacts.ui.home.Home
import com.example.catfacts.ui.mainLayout.CatBottomAppBar
import com.example.catfacts.ui.mainLayout.CatNavigationRail
import com.example.catfacts.ui.mainLayout.CatTopAppBar
import com.example.catfacts.ui.util.TaskNavigationType

/**
 * Composable function to display the main view based on the specified navigation type.
 *
 * @param navigationType The type of navigation to be used, e.g., [TaskNavigationType.BOTTOM_NAVIGATION] or
 * [TaskNavigationType.NAVIGATION_RAIL].
 * @param navController The NavHostController for navigating between destinations.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    navigationType: TaskNavigationType,
    navController: NavHostController = rememberNavController(),
) {
    val currentBackStack by navController.currentBackStackEntryAsState()

    if (navigationType == TaskNavigationType.BOTTOM_NAVIGATION) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        CatTopAppBar()
                    },
                )
            },
            bottomBar = {
                CatBottomAppBar(
                    showHomePage = {
                        if (canNavigate(currentBackStack, Destinations.Home.name)) {
                            navController.navigate(Destinations.Home.name) {
                                popUpTo(Destinations.Home.name) {
                                    // inclusive zorgt ervoor dat de homepage zelf niet meegepopped word
                                    inclusive = false
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    showFavoritePage = { if (canNavigate(currentBackStack, Destinations.Favorite.name)) navController.navigate(Destinations.Favorite.name) },
                )
            },
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = Destinations.Home.name,
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = dimensionResource(R.dimen.padding_screen_borders)),
            ) {
                composable(
                    Destinations.Home.name,

                ) {
                    Home()
                }
                composable(
                    Destinations.Favorite.name,
                ) {
                    FavoritePage()
                }
            }
        }
    } else {
        Row {
            AnimatedVisibility(visible = navigationType == TaskNavigationType.NAVIGATION_RAIL) {
                CatNavigationRail(
                    showHomePage = {
                        if (canNavigate(currentBackStack, Destinations.Home.name)) {
                            navController.navigate(Destinations.Home.name) {
                                popUpTo(Destinations.Home.name) {
                                    // inclusive zorgt ervoor dat de homepage zelf niet meegepopped word
                                    inclusive = false
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    showFavoritePage = { if (canNavigate(currentBackStack, Destinations.Favorite.name)) navController.navigate(Destinations.Favorite.name) },
                )
            }
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            CatTopAppBar()
                        },
                    )
                },

            ) { innerPadding ->

                NavHost(
                    navController = navController,
                    startDestination = Destinations.Home.name,
                    modifier = Modifier
                        .padding(innerPadding),
                ) {
                    composable(

                        Destinations.Home.name,

                    ) {
                        Home()
                    }
                    composable(
                        Destinations.Favorite.name,

                    ) {
                        FavoritePage()
                    }
                }
            }
        }
    }
}

/**
 * Enum class representing different destinations in the app.
 */
enum class Destinations {
    Home,
    Favorite,
}

/**
 * Checks whether navigation is allowed to the specified destination based on the current back stack entry.
 *
 * @param current The current back stack entry.
 * @param destination The destination to navigate to.
 * @return True if navigation is allowed, false otherwise.
 */
private fun canNavigate(current: NavBackStackEntry?, destination: String): Boolean {
    val currentDest = current?.destination?.route

    return if (current != null) currentDest != destination else true
}
