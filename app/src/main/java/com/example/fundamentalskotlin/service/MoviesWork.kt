package com.example.fundamentalskotlin.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.fundamentalskotlin.api.MoviesLoading
import com.example.fundamentalskotlin.cache.MovieRepositoryImpl
import com.example.fundamentalskotlin.data.Movie

class MoviesWork(context: Context,
                 workerParams: WorkerParameters,
                 private val moviesLoading: MoviesLoading,
                 private val repositoryImpl: MovieRepositoryImpl
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        val movies = moviesLoading.loadingMoviesApi()
        repositoryImpl.updateMoviesCache(movies)

        val outputData = createOutputData(movies)
        return Result.success(outputData)
    }

    private fun createOutputData(movies: List<Movie>): Data {
        val idMovies = movies.map { movie -> movie.id }.toIntArray()
        return Data.Builder()
            .putIntArray(ID_MOVIES_KEY, idMovies)
            .build()
    }

    companion object {
        const val ID_MOVIES_KEY = "idMovies_key"
    }
}


