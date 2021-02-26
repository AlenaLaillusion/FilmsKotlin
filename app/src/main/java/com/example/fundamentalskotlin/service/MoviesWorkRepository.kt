package com.example.fundamentalskotlin.service

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters


class MoviesWorkRepository(context: Context,
                           workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams){

    override suspend fun doWork(): Result {
        Log.d("doWork", "Start doWork")

        val moviesConstrainedRequest =
            OneTimeWorkRequestBuilder<MoviesWork>()
                .build()

        val actorsConstrainedRequest =
            OneTimeWorkRequestBuilder<ActorsWork>()
                .build()

        WorkManager.getInstance(applicationContext)
            .beginWith(moviesConstrainedRequest)
            .then(actorsConstrainedRequest)
            .enqueue()

        return Result.success()
    }

}