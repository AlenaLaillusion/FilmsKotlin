package com.example.fundamentalskotlin.cache.repository

import com.example.fundamentalskotlin.cache.MoviesDatabase
import com.example.fundamentalskotlin.data.Actor
import com.example.fundamentalskotlin.domain.ActorEntity
import com.example.fundamentalskotlin.domain.ActorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ActorsRepositoryImpl: ActorRepository {

    private val moviesDB = MoviesDatabase.create

    override suspend fun getAllActorsByMovie(movieId: Int): List<Actor> =
        withContext(Dispatchers.IO) {
            moviesDB.actorsDao.getAllByMovieId(movieId).map {toActor(it)}
        }

    override suspend fun rewriteActorsByMovieIntoDB(actors: List<Actor>, movieId: Int) =
        withContext(Dispatchers.IO) {
            moviesDB.actorsDao.deleteByMovieId(movieId)
            moviesDB.actorsDao.insertAll(actors.map { toActorEntity(it, movieId) })
        }

    private fun toActorEntity(actor: Actor, movieId: Int) =
        ActorEntity(
            id = null,
            actorId = actor.id,
            name = actor.name,
            image = actor.picture,
            movie = movieId.toLong()
        )

    private fun toActor (actorEntity: ActorEntity) = Actor (
    id = actorEntity.actorId,
    name = actorEntity.name,
    picture = actorEntity.image
    )

}