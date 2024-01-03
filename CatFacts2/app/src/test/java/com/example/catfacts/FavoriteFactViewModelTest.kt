package com.example.catfacts

import com.example.catfacts.data.Fact
import com.example.catfacts.data.database.asDomainFacts
import com.example.catfacts.data.toFacts
import com.example.catfacts.fake.FakeApiFactsRepository
import com.example.catfacts.fake.FakeDataSource
import com.example.catfacts.fake.FakeFactsApiService
import com.example.catfacts.ui.states.FactApiState
import com.example.catfacts.viewmodels.FavoriteFactViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteFactViewModelTest {
    private lateinit var viewModel: FavoriteFactViewModel
    private lateinit var apiState: FactApiState
    private lateinit var uiList: List<Fact>

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    /**
     * Initializes the [FavoriteFactViewModel] and sets up the initial [apiState] and [uiList].
     */
    @Before
    fun init() = runTest {
        viewModel = FavoriteFactViewModel(
            factRepository = FakeApiFactsRepository(FakeFactsApiService()),
        )

        apiState = viewModel.apiState
        when (apiState) {
            is FactApiState.Success -> {
                uiList = viewModel.uiListState.first()
            }
            else -> { throw AssertionError() }
        }
    }

    /**
     * Tests the [getFavoriteFactsTest] function to ensure it returns the correct list of favorite facts.
     */
    @Test
    fun getFavoriteFactsTest() {
        Assert.assertEquals(uiList, FakeDataSource.favoFacts.asDomainFacts())
    }
}
