package com.example.fundamentalskotlin.domain

import com.example.fundamentalskotlin.data.Movie

interface MoviesRepository {
    suspend fun getAllMovies(): List<Movie>
    suspend fun writeMovieIntoDB(movie: Movie)
    suspend fun rewriteMoviesListIntoDB(movies: List<Movie>)
}