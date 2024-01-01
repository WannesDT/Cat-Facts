package com.example.catfacts.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.catfacts.CatsApplication
import com.example.catfacts.data.Fact
import com.example.catfacts.data.FactRepository
import com.example.catfacts.ui.states.FactApiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for managing favorite facts and their states.
 *
 * @param factRepository The repository for fetching and managing favorite facts.
 */
class FavoriteFactViewModel(
    private val factRepository: FactRepository,
) : ViewModel() {

    private val TAG = "FavoriteFactViewModel"

    /**
     * StateFlow representing the list of favorite facts.
     */
    lateinit var uiListState: StateFlow<List<Fact>>

    /**
     * Current state of the Favorite Fact ROOMdb response.
     */
    var apiState: FactApiState by mutableStateOf(FactApiState.Loading)
        private set
    init {
        getFavoriteFacts()
    }

    /**
     * Removes a fact from the list of favorites and deletes it from the database.
     *
     * @param fact The fact to be removed from favorites.
     */
    fun removeFavorite(fact: Fact) {
        viewModelScope.launch {
            try {
                factRepository.deleteFavoriteFact(fact)
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    /**
     * Fetches the list of favorite facts from the repository and updates the state accordingly.
     */
    fun getFavoriteFacts() {
        viewModelScope.launch {
            apiState = try {
                uiListState = factRepository.getFavoriteFacts()
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = listOf(),
                    )
                FactApiState.Success
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
                FactApiState.Error
            }
        }
    }
    companion object {
        /**
         * Factory for creating instances of [FavoriteFactViewModel].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CatsApplication)
                val factRepository = application.container.factRepository
                FavoriteFactViewModel(factRepository)
            }
        }
    }
}
