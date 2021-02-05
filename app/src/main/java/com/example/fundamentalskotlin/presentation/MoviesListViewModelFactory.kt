package com.example.fundamentalskotlin.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi

//Movies List
class MoviesListViewModelFactory(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModelProvider.Factory {

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        FragmentMoviesListViewModel::class.java -> FragmentMoviesListViewModel(
            context = context,
            dispatcher = dispatcher
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}

