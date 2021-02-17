package com.example.fundamentalskotlin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fundamentalskotlin.api.RetrofitModule
import com.example.fundamentalskotlin.cache.ActorsRepositoryImpl
import com.example.fundamentalskotlin.data.Movie
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.create

class MoviesDetailsViewModelFactory(private val movie: Movie) : ViewModelProvider.Factory {

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        FragmentMoviesDetailsViewModel::class.java -> FragmentMoviesDetailsViewModel(
            movie,
            moviesApi = RetrofitModule.retrofit.create(),
            actorRepository =  ActorsRepositoryImpl()
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}
