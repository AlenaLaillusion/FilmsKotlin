package com.example.fundamentalskotlin.api

import com.example.fundamentalskotlin.cache.ActorsRepositoryImpl
import com.example.fundamentalskotlin.data.Actor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ActorsLoading {

    suspend fun loadingActorsApi(movieId: Int) : List<Actor>

    suspend fun  loadActorsDb(movieId: Int) : List<Actor>
}

class ActorsLoadingRepositories (
    private val moviesApi: MoviesApi,
    private val actorRepository: ActorsRepositoryImpl
) : ActorsLoading {

    override suspend fun loadingActorsApi(movieId: Int): List<Actor> =
        withContext(Dispatchers.IO) {
            val actorsResponce = moviesApi.getActors(movieId)
            parceActors(actorsResponce.actors)
        }

    override suspend fun loadActorsDb(movieId: Int): List<Actor> =
        withContext(Dispatchers.IO) {
            actorRepository.getAllActors(movieId)
        }
}

