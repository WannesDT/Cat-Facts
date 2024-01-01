package com.example.catfacts.ui.states

/**
 * Sealed interface representing the different states of the Fact API response.
 */
sealed interface FactApiState {
    object Error : FactApiState
    object NoInternet : FactApiState
    object Loading : FactApiState
    object Success : FactApiState
}
