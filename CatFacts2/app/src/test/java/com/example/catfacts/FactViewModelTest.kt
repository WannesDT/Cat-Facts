package com.example.catfacts

import com.example.catfacts.fake.FakeApiFactsRepository
import com.example.catfacts.fake.FakeDataSource
import com.example.catfacts.viewmodels.FactViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class FactViewModelTest {
    private val someFact = "some fact 2"

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun settingNameChangesState() {
        val viewModel = FactViewModel(
            factRepository = FakeApiFactsRepository(),
        )
        Assert.assertEquals(viewModel.getApiFact(), FakeDataSource.fact1)
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
