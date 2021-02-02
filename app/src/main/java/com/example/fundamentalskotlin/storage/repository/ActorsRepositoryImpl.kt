package com.example.fundamentalskotlin.storage.repository

import com.example.fundamentalskotlin.data.Actor
import com.example.fundamentalskotlin.storage.MoviesDatabase
import com.example.fundamentalskotlin.storage.mappers.ActorMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface ActorRepository {
    suspend fun getAllActorsByMovie(movieId: Int): List<Actor>
    suspend fun rewriteActorsByMovieIntoDB(actors: List<Actor>, movieId: Int)
}

class ActorsRepositoryImpl: ActorRepository {
    private val moviesDB = MoviesDatabase.instance

    override suspend fun getAllActorsByMovie(movieId: Int): List<Actor> =
        withContext(Dispatchers.IO) {
            moviesDB.actorsDao().getAllByMovieId(movieId).map {ActorMapper.toActorDomain(it)}
        }

    override suspend fun rewriteActorsByMovieIntoDB(actors: List<Actor>, movieId: Int) =
        withContext(Dispatchers.IO) {
            moviesDB.actorsDao().deleteByMovieId(movieId)
            moviesDB.actorsDao().insertAll(actors.map { ActorMapper.toActorEntity(it, movieId) })
        }

}