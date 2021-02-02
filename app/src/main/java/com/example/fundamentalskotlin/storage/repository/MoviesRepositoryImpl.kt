package com.example.fundamentalskotlin.storage.repository

import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.storage.MoviesDatabase
import com.example.fundamentalskotlin.storage.mappers.MovieMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MoviesRepository {
    suspend fun getAllMovies(): List<Movie>
    suspend fun writeMovieIntoDB(movie: Movie)
    suspend fun rewriteMoviesListIntoDB(movies: List<Movie>)
}

class MoviesRepositoryImpl : MoviesRepository {
    private val moviesDB = MoviesDatabase.instance

    override suspend fun writeMovieIntoDB(movie: Movie) = withContext(Dispatchers.IO) {
        moviesDB.moviesDao().insert(MovieMapper.toMovieEntity(movie))
    }

    override suspend fun rewriteMoviesListIntoDB(movies: List<Movie>) =
        withContext(Dispatchers.IO) {
            moviesDB.moviesDao().deleteAll()
            moviesDB.moviesDao().insertAll(movies.map { MovieMapper.toMovieEntity(it) })
        }

    override suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        moviesDB.moviesDao().getAll().map { MovieMapper.toMovieDomain(it) }
    }
}