package com.example.fundamentalskotlin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fundamentalskotlin.api.RetrofitHolder
import com.example.fundamentalskotlin.cache.repository.ActorsRepositoryImpl
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.create

class MoviesDetailsViewModelFactory : ViewModelProvider.Factory {

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        FragmentMoviesDetailsViewModel::class.java -> FragmentMoviesDetailsViewModel(
            apiService = RetrofitHolder.retrofit.create(),
            repository = ActorsRepositoryImpl()
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}

