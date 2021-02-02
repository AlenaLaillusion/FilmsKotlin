package com.example.fundamentalskotlin.api

import com.example.fundamentalskotlin.BuildConfig
import com.example.fundamentalskotlin.data.Actor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun convertActorDtoToDomain(actorsDto: List<ActorDto>): List<Actor> =
    withContext(Dispatchers.Default) {
        actorsDto.map { actorDto ->
            Actor(
                id = actorDto.id,
                name = actorDto.name,
                picture = actorDto.image?.let { BuildConfig.IMAGE_URL + actorDto.image }
            )
        }
    }