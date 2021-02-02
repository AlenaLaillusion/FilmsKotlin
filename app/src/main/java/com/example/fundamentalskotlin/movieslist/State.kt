package com.example.fundamentalskotlin.movieslist

sealed class State {
    object Init : State()
    object Loading : State()
    object Error : State()
    object Success : State()
}