package com.example.catfacts.favorite

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.catfacts.data.Fact
import com.example.catfacts.ui.favorite.FavoriteCard
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * UI test class for the [FavoriteCard] composable.
 */
class FavoriteCardTest {
    private val fact = Fact("fact1")

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Set up the test environment by providing the [FavoriteCard] composable with the [fact].
     */
    @Before
    fun init() {
        composeTestRule.setContent {
            FavoriteCard(fact = fact, onRemoveCLicked = {})
        }
    }

    /**
     * Test to verify if the displayed content of the FavoriteCard matches the fact's content.
     */
    @Test
    fun isFactDisplayed() {
        composeTestRule
            .onNodeWithText(fact.content)
            .assertIsDisplayed()
    }
}
