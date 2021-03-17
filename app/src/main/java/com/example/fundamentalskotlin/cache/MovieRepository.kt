package com.example.fundamentalskotlin.cache

import com.example.fundamentalskotlin.data.Movie

interface MovieRepository {

    suspend fun getAllMovies(): List<Movie>

    suspend fun addMovieCache(movie: Movie)

    suspend fun updateMoviesCache(movies: List<Movie>)

    suspend fun getMovieId(movieId: Int): Movie
}