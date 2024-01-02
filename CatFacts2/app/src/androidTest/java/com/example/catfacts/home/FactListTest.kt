package com.example.catfacts.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.catfacts.data.Fact
import com.example.catfacts.ui.home.RandomFactList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FactListTest {
    private val fact1 = Fact("fact1")
    private val fact2 = Fact("fact2")
    private var someFacts by mutableStateOf(mutableListOf<Fact>(fact1, fact2))

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        composeTestRule.setContent {
            RandomFactList(listOfFacts = someFacts) {}
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
