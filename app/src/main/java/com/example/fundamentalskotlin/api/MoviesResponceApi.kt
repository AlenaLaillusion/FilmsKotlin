package com.example.fundamentalskotlin.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponce (
    val results: List<MovieResponce>
)

@Serializable
data class MovieResponce(
    val id: Int,
    val title: String,
    val overview: String,
    val adult: Boolean,
    val runtime: Int? = null,

    @SerialName("genre_ids")
    val genreIDS: List<Int>,

    @SerialName("poster_path")
    val poster: String,

    @SerialName("backdrop_path")
    val backdrop: String,

    @SerialName("vote_average")
    val ratings: Float,

    @SerialName("vote_count")
    val reviews: Int
)