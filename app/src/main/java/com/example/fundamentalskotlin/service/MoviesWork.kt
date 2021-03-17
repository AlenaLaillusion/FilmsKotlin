package com.example.fundamentalskotlin.service

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.fundamentalskotlin.api.MoviesLoading
import com.example.fundamentalskotlin.cache.MovieRepositoryImpl
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.data.MovieTopNotification

class MoviesWork(context: Context,
                 workerParams: WorkerParameters,
                 private val moviesLoading: MoviesLoading,
                 private val repositoryImpl: MovieRepositoryImpl,
                 private val movieTopNotification: MovieTopNotification,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        val movies = moviesLoading.loadingMoviesApi()
        repositoryImpl.updateMoviesCache(movies)

        val movieIdTop = movies.maxBy { it.ratings }
        movieIdTop.let { movieTopNotification.showNotification(it!!) }

        Log.d("doWork", "MoviesWork doWork() + ${movieIdTop}")

        val outputData = createOutputData(movies)

        Log.d("doWork", "MoviesWork + ${Result.success(outputData)}")
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


