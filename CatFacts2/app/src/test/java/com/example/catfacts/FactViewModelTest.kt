package com.example.catfacts

import com.example.catfacts.data.Fact
import com.example.catfacts.fake.FakeApiFactsRepository
import com.example.catfacts.fake.FakeDataSource
import com.example.catfacts.fake.FakeFactsApiService
import com.example.catfacts.ui.states.FactApiState
import com.example.catfacts.viewmodels.FactViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Unit tests for [FactViewModel] class.
 */
class FactViewModelTest {
    private lateinit var viewModel: FactViewModel
    private lateinit var apiState: FactApiState

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    /**
     * Retrieves the list of facts based on the current [apiState].
     * Throws [AssertionError] if [apiState] is not [FactApiState.Success].
     *
     * @return The list of facts.
     */
    private fun getFacts(): List<Fact> {
        val thisFacts: List<Fact>

        when (apiState) {
            is FactApiState.Success -> { thisFacts = viewModel.listOfFacts }
            else -> { throw AssertionError() }
        }

        return thisFacts
    }

    /**
     * Initializes the [FactViewModel] and sets up the initial [apiState].
     */
    @Before
    fun init() {
        viewModel = FactViewModel(
            factRepository = FakeApiFactsRepository(FakeFactsApiService()),
        )

        apiState = viewModel.apiState
        when (apiState) {
            is FactApiState.Success -> return
            else -> { throw AssertionError() }
        }
    }

    /**
     * Tests the [getFacts] function to ensure it returns the correct list of facts.
     */
    @Test
    fun getFactsTest() {
        Assert.assertEquals(getFacts().first(), FakeDataSource.fact1)
    }

    /**
     * Tests the functions to set and remove the "isFavorite" flag for a fact.
     */
    @Test
    fun setAndRemoveIsFavorite() {
        viewModel.addFavorite(getFacts().first())
        Assert.assertEquals(getFacts().first().isFavorite, true)
        viewModel.removeFavorite(getFacts().first())
        Assert.assertEquals(getFacts().first().isFavorite, false)
    }
}

