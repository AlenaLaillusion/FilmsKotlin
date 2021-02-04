package com.example.fundamentalskotlin.domain

sealed class State {
    object Init : State()
    object Loading : State()
    object Error : State()
    object Success : State()
}