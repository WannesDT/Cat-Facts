package com.example.catfacts.ui.states

sealed interface FactApiState {
    object Error : FactApiState
    object NoInternet : FactApiState
    object Loading : FactApiState
    object Success : FactApiState
}
