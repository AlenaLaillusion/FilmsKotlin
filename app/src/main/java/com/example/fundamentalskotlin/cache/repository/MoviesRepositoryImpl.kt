package com.example.fundamentalskotlin.cache.repository

import com.example.fundamentalskotlin.cache.MoviesDatabase
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.domain.MovieEntity
import com.example.fundamentalskotlin.domain.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MoviesRepositoryImpl : MoviesRepository {
    private val moviesDB = MoviesDatabase.create

    override suspend fun writeMovieIntoDB(movie: Movie) = withContext(Dispatchers.IO) {
        moviesDB.moviesDao.insert(toMovieEntity(movie))
    }

    override suspend fun rewriteMoviesListIntoDB(movies: List<Movie>) =
        withContext(Dispatchers.IO) {
            moviesDB.moviesDao.deleteAll()
            moviesDB.moviesDao.insertAll(movies.map { toMovieEntity(it) })
        }

    override suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        moviesDB.moviesDao.getAll().map { toMovie(it) }
    }

    private fun toMovie(movieEntity: MovieEntity) = Movie(
        id = movieEntity.id.toInt(),
        title = movieEntity.title,
        overview = movieEntity.overview,
        poster = movieEntity.poster,
        backdrop = movieEntity.backdrop,
        ratings = movieEntity.ratings,
        adult = movieEntity.adult,
        runtime = movieEntity.runtime,
        reviews = movieEntity.reviews,
        genres = movieEntity.genres.split(",").map { it.trim() },
        like = movieEntity.like
    )

    private fun toMovieEntity(movie: Movie) =
        MovieEntity(
            id = movie.id.toLong(),
            title = movie.title,
            overview = movie.overview,
            poster = movie.poster,
            backdrop = movie.backdrop,
            ratings = movie.ratings,
            adult = movie.adult,
            runtime = movie.runtime,
            reviews = movie.reviews,
            genres = movie.genres.joinToString(","),
            like = movie.like
        )
}