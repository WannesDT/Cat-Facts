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
import com.example.catfacts.ui.states.FactApiState
import kotlinx.coroutines.launch
import okio.IOException

class FactViewModel(
    private val factRepository: FactRepository,
) : ViewModel() {

    private val TAG = "FactViewModel"
    var apiState: FactApiState by mutableStateOf(FactApiState.Loading)
        private set

    var isFavoriteState: Boolean by mutableStateOf(false)
        private set

    init {
        getApiFact()
    }

    fun addFavorite(fact: Fact) {
        viewModelScope.launch {
            try {
                isFavoriteState = true
                factRepository.insertFavoriteFact(fact)
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    fun removeFavorite(fact: Fact) {
        viewModelScope.launch {
            try {
                isFavoriteState = false
                factRepository.deleteFavoriteFact(fact)
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    fun getApiFact() {
        viewModelScope.launch {
            apiState = try {
                isFavoriteState = false
                FactApiState.Success(factRepository.getRandomFact())
            } catch (ex: IOException) {
                FactApiState.NoInternet
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
                FactApiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CatsApplication)
                val factRepository = application.container.factRepository
                FactViewModel(factRepository)
            }
        }
    }
}
