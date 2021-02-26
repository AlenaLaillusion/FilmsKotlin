package com.example.fundamentalskotlin.cache

import com.example.fundamentalskotlin.data.Actor
import com.example.fundamentalskotlin.domain.ActorsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActorsRepositoryImpl : ActorsRepository {

    private val moviesDataBase = MoviesDataBase.create

    override suspend fun getAllActors(movieId: Int): List<Actor> = withContext(Dispatchers.IO) {
        moviesDataBase.moviesDao.getActorsByMovieId(movieId).map { toActor(it) }
    }

    override suspend fun updateActorsCache(actors: List<Actor>, movieId: Int) = withContext(Dispatchers.IO){
        moviesDataBase.moviesDao.deleteActorsByMovieId(movieId)
        moviesDataBase.moviesDao.insertActors(actors.map { toActorEntity(it, movieId) })
    }


    private fun toActor(actorsEntity: ActorsEntity) = Actor(
        id = actorsEntity.actorId,
        name = actorsEntity.name,
        picture = actorsEntity.picture
    )

    private fun toActorEntity(actor: Actor, movieId: Int) = ActorsEntity(
        id = null,
        actorId = actor.id,
        name = actor.name,
        picture = actor.picture,
        movieId = movieId
    )

}