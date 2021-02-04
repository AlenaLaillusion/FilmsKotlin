package com.example.fundamentalskotlin.domain

import com.example.fundamentalskotlin.data.Actor

interface ActorRepository {
    suspend fun getAllActorsByMovie(movieId: Int): List<Actor>
    suspend fun rewriteActorsByMovieIntoDB(actors: List<Actor>, movieId: Int)
}