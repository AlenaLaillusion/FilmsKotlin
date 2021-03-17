package com.example.fundamentalskotlin.cache

import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.domain.MoviesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl : MovieRepository {

    private val moviesDataBase = MoviesDataBase.create

    override suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        moviesDataBase.moviesDao.getAllMovies().map { toMovie(it) }
    }

    override suspend fun addMovieCache(movie: Movie) = withContext(Dispatchers.IO) {
        moviesDataBase.moviesDao.insert(toMovieEntity(movie))
    }

    override suspend fun updateMoviesCache(movies: List<Movie>)  = withContext(Dispatchers.IO) {
        moviesDataBase.moviesDao.deleteAll()
        moviesDataBase.moviesDao.insertAll(movies.map { toMovieEntity(it) })
    }

    override suspend fun getMovieId(movieId: Int): Movie = withContext(Dispatchers.IO) {
        val movieEntity = moviesDataBase.moviesDao.getMovieId(movieId)
        toMovie(movieEntity)
    }

    private fun toMovie(moviesEntity: MoviesEntity) = Movie(
        id = moviesEntity.id,
        title = moviesEntity.title,
        overview = moviesEntity.overview,
        poster = moviesEntity.poster,
        backdrop = moviesEntity.backdrop,
        ratings = moviesEntity.ratings,
        adult = moviesEntity.adult,
        runtime = moviesEntity.runtime,
        reviews = moviesEntity.reviews,
        genres = moviesEntity.genres.split(",").map { it.trim() },
        like = moviesEntity.like
    )

    private fun toMovieEntity (movie: Movie) = MoviesEntity(
        id = movie.id,
        title = movie.title,
        overview = movie.overview,
        poster = movie.poster,
        backdrop = movie.backdrop,
        ratings = movie.ratings,
        adult = movie.adult,
        runtime = movie.runtime,
        reviews = movie.reviews,
        genres = movie.genres.joinToString (", "),
        like = movie.like
    )

}