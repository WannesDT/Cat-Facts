package com.example.catfacts.ui.states

import com.example.catfacts.data.Fact

sealed interface FactApiState {
    object Error : FactApiState
    object NoInternet : FactApiState
    object Loading : FactApiState
    data class Success(val fact: Fact) : FactApiState
}
