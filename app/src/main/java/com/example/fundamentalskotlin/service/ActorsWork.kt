package com.example.fundamentalskotlin.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.fundamentalskotlin.api.ActorsLoading
import com.example.fundamentalskotlin.cache.ActorsRepositoryImpl

class ActorsWork(context: Context,
                 workerParams: WorkerParameters,
                private val actorsRepositoryImpl: ActorsRepositoryImpl,
                 private val actorsLoading: ActorsLoading
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        val moviesID = inputData.getIntArray(MoviesWork.ID_MOVIES_KEY)

        moviesID?.forEach { movieId ->
            val actors = actorsLoading.loadingActorsApi(movieId)
            actorsRepositoryImpl.updateActorsCache(actors, movieId)
        }
        return Result.success()
    }
}