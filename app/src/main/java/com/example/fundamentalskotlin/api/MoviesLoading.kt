package com.example.fundamentalskotlin.api

import com.example.fundamentalskotlin.data.Movie

interface MoviesLoading {

    suspend fun loadingMoviesApi(): List<Movie>

}


