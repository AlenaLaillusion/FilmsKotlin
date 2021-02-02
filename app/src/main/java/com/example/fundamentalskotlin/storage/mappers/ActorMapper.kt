package com.example.fundamentalskotlin.storage.mappers

import com.example.fundamentalskotlin.data.Actor
import com.example.fundamentalskotlin.storage.entitys.ActorEntity

object ActorMapper {

    internal fun toActorDomain(actorEntity: ActorEntity) = Actor (
        id = actorEntity.actorId,
        name = actorEntity.name,
        picture = actorEntity.image
    )

    internal fun toActorEntity(actorDomain: Actor, movieId: Int) = ActorEntity(
        id = null,
        actorId = actorDomain.id,
        name = actorDomain.name,
        image = actorDomain.picture,
        movie = movieId.toLong()
    )
}