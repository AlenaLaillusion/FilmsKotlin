package com.example.fundamentalskotlin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fundamentalskotlin.data.Movie
import kotlinx.serialization.ExperimentalSerializationApi

class MoviesDetailsViewModelFactory(private val movie: Movie) : ViewModelProvider.Factory {

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        FragmentMoviesDetailsViewModel::class.java -> FragmentMoviesDetailsViewModel(movie)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}
