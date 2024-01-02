package com.example.catfacts.favorite

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.catfacts.data.Fact
import com.example.catfacts.ui.favorite.FavoriteCard
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteCardTest {
    private val fact = Fact("fact1")

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        composeTestRule.setContent {
            FavoriteCard(fact = fact, onRemoveCLicked = {})
        }
    }

    @Test
    fun isFactDisplayed() {
        composeTestRule
            .onNodeWithText(fact.content)
            .assertIsDisplayed()
    }
}
