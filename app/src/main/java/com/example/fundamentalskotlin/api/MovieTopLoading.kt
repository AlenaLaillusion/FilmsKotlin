package com.example.fundamentalskotlin.api

import com.example.fundamentalskotlin.cache.MovieRepositoryImpl
import com.example.fundamentalskotlin.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MovieTopLoading {

        suspend fun loadingMovieTop(movieId: Int) : Movie
}

class MovieTopLoadingRepositories (
        private val movieRepository: MovieRepositoryImpl
) : MovieTopLoading {

        override suspend fun loadingMovieTop(movieId: Int): Movie =
                withContext(Dispatchers.IO) {
                        movieRepository.getMovieId(movieId)
                }

}