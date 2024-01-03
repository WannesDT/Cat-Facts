package com.example.catfacts

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.catfacts.ui.MainView
import com.example.catfacts.ui.util.TaskNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Test class for navigation within the Compose UI using [ComposeNavigator].
 */
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    /**
     * Set up the Compose UI with a test navigation host controller and the [MainView].
     */
    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainView(
                navController = navController,
                navigationType = TaskNavigationType.BOTTOM_NAVIGATION,
            )
        }
    }

    /**
     * Perform the navigation action to go to the Home destination.
     */
    private fun goToHome() {
        composeTestRule
            .onNodeWithContentDescription("ga naar Home")
            .performClick()
    }

    /**
     * Perform the navigation action to go to the Favorite destination.
     */
    private fun goToFavorite() {
        // R.string.appbar_icon_favorite_description
        composeTestRule
            .onNodeWithContentDescription("ga naar Favoriet")
            .performClick()
    }

    /**
     * Test to verify that the start destination is displayed.
     */
    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithContentDescription("image")
            .assertIsDisplayed()
    }

    /**
     * Test navigation to the Favorites destination and verify if the title is displayed.
     */
    @Test
    fun navigateToFavorites() {
        goToFavorite()
        composeTestRule
            .onNodeWithText("Saved Facts")
            .assertIsDisplayed()
    }

    /**
     * Test to verify that the "next fact" text is displayed after navigating to the Home destination.
     */
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun addNextFactDisplayed(): Unit = run {
        goToHome()
        composeTestRule
            .waitUntilExactlyOneExists(hasText("next fact"))

        composeTestRule
            .onNodeWithText("next fact")
            .assertIsDisplayed()
    }
}
