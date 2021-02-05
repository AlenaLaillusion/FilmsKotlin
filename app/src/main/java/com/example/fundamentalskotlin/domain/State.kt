package com.example.fundamentalskotlin.domain

sealed class State {
    class Init : State()
    class Loading : State()
    class Error : State()
    class Success : State()
}