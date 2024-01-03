package com.example.catfacts.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.catfacts.data.Fact
import com.example.catfacts.ui.home.FactCard
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * UI test class for the [FactCard] composable.
 */
class FactCardTest {
    private var fact by mutableStateOf(Fact("fact1"))

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Set up the test environment by providing the [FactCard] composable with the [fact].
     */
    @Before
    fun init() {
        composeTestRule.setContent {
            FactCard(fact = fact, onFavoriteClicked = { fact = fact.copy(isFavorite = !it.isFavorite) })
        }
    }

    /**
     * Test to verify if the displayed content of the FactCard matches the fact's content.
     */
    @Test
    fun isFactDisplayed() {
        composeTestRule
            .onNodeWithText(fact.content)
            .assertIsDisplayed()
    }

    /**
     * Test to verify if toggling the favorite icon works as expected.
     */
    @Test
    fun toggleFavorite() {
        composeTestRule
            .onNodeWithText(fact.content)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithContentDescription("favorite")
            .performClick()

        composeTestRule
            .onNodeWithTag("unfavorite")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag("favorite")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithContentDescription("delete")
            .performClick()

        composeTestRule
            .onNodeWithTag("unfavorite")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithTag("favorite")
            .assertIsDisplayed()
    }
}
