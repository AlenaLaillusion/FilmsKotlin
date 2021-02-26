package com.example.fundamentalskotlin.api

import com.example.fundamentalskotlin.data.Actor

interface ActorsLoading {

    suspend fun loadingActorsApi(movieId: Int) : List<Actor>
}