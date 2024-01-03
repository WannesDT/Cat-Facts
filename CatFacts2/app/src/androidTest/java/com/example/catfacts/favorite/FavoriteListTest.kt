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

class FavoriteListTest {
    private val fact1 = Fact("fact1")
    private val fact2 = Fact("fact2")
    private var someFacts by mutableStateOf(listOf(fact1, fact2))

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        composeTestRule.setContent {
            FavoriteList(factsList = someFacts) { fact ->
                removeFact(fact)
            }
        }
    }

    private fun removeFact(fact: Fact) {
        someFacts = someFacts.filterNot { item -> fact.content == item.content }.toMutableList()
    }

    @Test
    fun areAllItemsDisplayed() {
        composeTestRule
            .onNodeWithText(fact1.content)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(fact2.content)
            .assertIsDisplayed()
    }

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
