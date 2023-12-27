package com.example.catfacts.ui.states

sealed interface FavoriteApiState {
    object Error : FavoriteApiState
    object Loading : FavoriteApiState
    object Success : FavoriteApiState
}
