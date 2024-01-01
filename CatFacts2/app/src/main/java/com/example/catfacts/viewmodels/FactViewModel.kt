package com.example.catfacts.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.catfacts.CatsApplication
import com.example.catfacts.data.Fact
import com.example.catfacts.data.FactRepository
import com.example.catfacts.data.updated
import com.example.catfacts.ui.states.FactApiState
import kotlinx.coroutines.launch
import okio.IOException

/**
 * ViewModel for managing facts and their states.
 *
 * @param factRepository The repository for fetching and managing facts.
 */
class FactViewModel(
    private val factRepository: FactRepository,
) : ViewModel() {

    private val TAG = "FactViewModel"

    /**
     * Current state of the Fact API response.
     */
    var apiState: FactApiState by mutableStateOf(FactApiState.Loading)
        private set

    /**
     * List of facts fetched from the API.
     */
    var listOfFacts: List<Fact> by mutableStateOf(mutableListOf())
        private set

    init {
        getApiFact()
    }

    /**
     * Adds a fact to the list of favorites and inserts it into the database.
     *
     * @param fact The fact to be added to favorites.
     */
    fun addFavorite(fact: Fact) {
        viewModelScope.launch {
            try {
                listOfFacts = listOfFacts.updated(listOfFacts.indexOf(fact), Fact(fact.content, true))
                factRepository.insertFavoriteFact(fact)
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    /**
     * Removes a fact from the list of favorites and deletes it from the database.
     *
     * @param fact The fact to be removed from favorites.
     */
    fun removeFavorite(fact: Fact) {
        viewModelScope.launch {
            try {
                listOfFacts = listOfFacts.updated(listOfFacts.indexOf(fact), Fact(fact.content, false))
                factRepository.deleteFavoriteFact(fact)
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    /**
     * Fetches a fact from the API and updates the state accordingly.
     */
    fun getApiFact() {
        viewModelScope.launch {
            apiState = try {
                val fact = factRepository.getRandomFact()
                listOfFacts += fact
                FactApiState.Success
            } catch (ex: IOException) {
                FactApiState.NoInternet
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
                FactApiState.Error
            }
        }
    }
    companion object {
        /**
         * Factory for creating instances of [FactViewModel].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CatsApplication)
                val factRepository = application.container.factRepository
                FactViewModel(factRepository)
            }
        }
    }
}
