package com.example.fundamentalskotlin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fundamentalskotlin.MoviesApp
import com.example.fundamentalskotlin.api.RetrofitModule
import com.example.fundamentalskotlin.cache.MovieRepositoryImpl
import com.example.fundamentalskotlin.data.MovieTopNotification
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.create

//Movies List
class MoviesListViewModelFactory: ViewModelProvider.Factory {

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        FragmentMoviesListViewModel::class.java -> FragmentMoviesListViewModel(
             moviesApi = RetrofitModule.retrofit.create(),
             repositoryImpl = MovieRepositoryImpl(),
             movieTopNotification = MovieTopNotification(MoviesApp.context())
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}

