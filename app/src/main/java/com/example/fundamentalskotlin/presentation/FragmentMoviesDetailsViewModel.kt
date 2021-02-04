package com.example.fundamentalskotlin.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamentalskotlin.api.MovieApi
import com.example.fundamentalskotlin.api.convertActorDtoToDomain
import com.example.fundamentalskotlin.cache.repository.ActorsRepositoryImpl
import com.example.fundamentalskotlin.data.Actor
import kotlinx.coroutines.launch

class FragmentMoviesDetailsViewModel(
    private val apiService: MovieApi,
    private val repository: ActorsRepositoryImpl
) : ViewModel() {

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> get() = _actors

    fun getActors(movieId: Int) {
        viewModelScope.launch {
            loadActorsFromDb(movieId)
            loadActorsFromApi(movieId)
        }
    }

    private suspend fun loadActorsFromApi(movieId: Int) {
        try {
            val resultRequest = apiService.getActors(movieId = movieId)

            val actors = convertActorDtoToDomain(resultRequest.actors)
            _actors.value = actors

            if (!actors.isNullOrEmpty()) {
                repository.rewriteActorsByMovieIntoDB(actors, movieId)
        }
    } catch (e: Exception) {
            Log.e(
                FragmentMoviesDetailsViewModel::class.java.simpleName,
                "Error grab actors data ${e.message}")
        }
    }

    private suspend fun loadActorsFromDb(movieId: Int) {
        try {
            val actorsDb = repository.getAllActorsByMovie(movieId)

            if (actorsDb.isNotEmpty()) {
                _actors.value = actorsDb
            }
        } catch (e: Exception) {
            Log.e (
                FragmentMoviesDetailsViewModel::class.java.simpleName,
                "Error grab actors data ${e.message}")
        }
    }
}