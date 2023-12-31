package com.example.catfacts.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.catfacts.data.Fact
import com.example.catfacts.ui.favorite.FavoriteList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * UI test class for the [FavoriteList] composable.
 */
class FavoriteListTest {
    private val fact1 = Fact("fact1")
    private val fact2 = Fact("fact2")
    private var someFacts by mutableStateOf(listOf(fact1, fact2))

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Set up the test environment by providing the [FavoriteList] composable with [someFacts].
     */
    @Before
    fun init() {
        composeTestRule.setContent {
            FavoriteList(factsList = someFacts, onRemoveCLicked = { fact ->
                removeFact(fact)
            })
        }
    }

    /**
     * Helper function to remove a fact from the list.
     *
     * @param fact The fact to be removed.
     */
    private fun removeFact(fact: Fact) {
        someFacts = someFacts.filterNot { item -> fact.content == item.content }.toMutableList()
    }

    /**
     * Test to verify if all items in the FavoriteList are displayed.
     */
    @Test
    fun areAllItemsDisplayed() {
        composeTestRule
            .onNodeWithText(fact1.content)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(fact2.content)
            .assertIsDisplayed()
    }

    /**
     * Test to verify if a fact is removed from the FavoriteList.
     */
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun removeFactTest(): Unit = run {
        composeTestRule
            .onNodeWithText(fact1.content)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(fact1.content)
            .performClick()

        composeTestRule
            .waitUntilDoesNotExist(hasText(fact1.content))

        composeTestRule
            .onNodeWithText(fact1.content)
            .assertDoesNotExist()
    }
}
