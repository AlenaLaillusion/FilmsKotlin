package com.example.fundamentalskotlin.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorsResponce(
    @SerialName("cast")
    val actors: List<ActorResponce>
)

@Serializable
data class ActorResponce(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val picture: String? = null
)
