package com.example.catfacts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.catfacts.data.Fact
import com.example.catfacts.ui.favorite.FavoriteList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteListTest {
    private val fact1 = Fact("fact1")
    private val fact2 = Fact("fact2")
    private val someFacts = listOf(fact1, fact2)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        composeTestRule.setContent {
            FavoriteList(factsList = someFacts, onRemoveCLicked = {})
        }
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
}
