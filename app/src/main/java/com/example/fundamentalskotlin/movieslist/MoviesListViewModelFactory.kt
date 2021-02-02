package com.example.fundamentalskotlin.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fundamentalskotlin.api.RetrofitHolder
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.create

//Movies List
class MoviesListViewModelFactory : ViewModelProvider.Factory {

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        FragmentMoviesListViewModel::class.java -> FragmentMoviesListViewModel(
            apiService = RetrofitHolder.retrofit.create()
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}

