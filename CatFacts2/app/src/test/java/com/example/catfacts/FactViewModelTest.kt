package com.example.catfacts

import com.example.catfacts.data.Fact
import com.example.catfacts.fake.FakeApiFactsRepository
import com.example.catfacts.fake.FakeDataSource
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

class FactViewModelTest {
    private lateinit var viewModel: FactViewModel
    private lateinit var apiState: FactApiState

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private fun getFacts(): List<Fact> {
        val thisFacts: List<Fact>

        when (apiState) {
            is FactApiState.Success -> { thisFacts = viewModel.listOfFacts }
            else -> { throw AssertionError() }
        }

        return thisFacts
    }

    @Before
    fun init() {
        viewModel = FactViewModel(
            factRepository = FakeApiFactsRepository(),
        )

        apiState = viewModel.apiState
        when (apiState) {
            is FactApiState.Success -> return
            else -> { throw AssertionError() }
        }
    }

    @Test
    fun getFactsTest() {
        Assert.assertEquals(getFacts().first(), FakeDataSource.fact1)
    }

    @Test
    fun setAndRemoveIsFavorite() {
        viewModel.addFavorite(getFacts().first())
        Assert.assertEquals(getFacts().first().isFavorite, true)
        viewModel.removeFavorite(getFacts().first())
        Assert.assertEquals(getFacts().first().isFavorite, false)
    }
}

class TestDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
