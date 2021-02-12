package com.example.fundamentalskotlin.api

import kotlinx.serialization.Serializable

@Serializable
data class GenresResponce(
    val genres: List<GenreResponce>
)

@Serializable
data class GenreResponce (
    val id: Int,
    val name: String
)