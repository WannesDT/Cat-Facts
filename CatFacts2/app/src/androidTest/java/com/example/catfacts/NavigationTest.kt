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

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

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

    private fun goToHome() {
        composeTestRule
            .onNodeWithContentDescription("ga naar Home")
            .performClick()
    }

    private fun goToFavorite() {
        // R.string.appbar_icon_favorite_description
        composeTestRule
            .onNodeWithContentDescription("ga naar Favoriet")
            .performClick()
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithContentDescription("image")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToFavorites() {
        goToFavorite()
        composeTestRule
            .onNodeWithText("Saved Facts")
            .assertIsDisplayed()
    }

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
