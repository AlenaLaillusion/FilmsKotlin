package com.example.fundamentalskotlin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fundamentalskotlin.api.RetrofitHolder
import com.example.fundamentalskotlin.cache.repository.MoviesRepositoryImpl
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.create


//Movies List
class MoviesListViewModelFactory : ViewModelProvider.Factory {

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        FragmentMoviesListViewModel::class.java -> FragmentMoviesListViewModel(
            apiService = RetrofitHolder.retrofit.create(),
            repository = MoviesRepositoryImpl()
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}

