package com.example.fundamentalskotlin.cache

import com.example.fundamentalskotlin.data.Actor

interface ActorsRepository {

    suspend fun getAllActors(movieId: Int): List<Actor>

    suspend fun updateActorsCache(actors: List<Actor>, movieId: Int)
}