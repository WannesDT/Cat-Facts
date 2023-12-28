package com.example.catfacts

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.catfacts.ui.MainView
import com.example.catfacts.ui.util.TaskNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    private val someTaskName: String = "some task name"

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainView(navController = navController, navigationType = TaskNavigationType.BOTTOM_NAVIGATION)
        }
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithContentDescription("image")
            .assertIsDisplayed()
    }

    @Test
    fun navigateToFavorites() {
        // R.string.appbar_icon_favorite_description
        composeTestRule
            .onNodeWithContentDescription("ga naar Favoriet")
            .performClick()
        composeTestRule
            .onNodeWithText("About")
            .assertIsDisplayed()
    }

    @Test
    fun clickAddNextFact() {
        composeTestRule
            .onNodeWithContentDescription("Add")
            .performClick()
        composeTestRule
            .onNodeWithText("taskname")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Save")
            .assertIsDisplayed()
    }

    @Test
    fun canAddTask() {
        composeTestRule
            .onNodeWithContentDescription("Add")
            .performClick()
        composeTestRule
            .onNodeWithText("taskname")
            .performTextInput(someTaskName)
        composeTestRule
            .onNodeWithText("Save")
            .performClick()
        composeTestRule
            .onNodeWithText(someTaskName)
            .assertIsDisplayed()
    }
}
