package com.example.fundamentalskotlin.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamentalskotlin.R
import com.example.fundamentalskotlin.api.ActorsLoading
import com.example.fundamentalskotlin.api.MoviesApi
import com.example.fundamentalskotlin.api.parceActors
import com.example.fundamentalskotlin.cache.ActorsRepositoryImpl
import com.example.fundamentalskotlin.data.Actor
import com.example.fundamentalskotlin.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMoviesDetailsViewModel(
    private val movie: Movie,
    private val moviesApi: MoviesApi,
    private val actorRepository: ActorsRepositoryImpl
) : ViewModel(), ActorsLoading {

    private val _clickedMovie = MutableLiveData<Movie>()
    val clickedMovie: LiveData<Movie> get() = _clickedMovie

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> get() = _actors

       fun loadMovie() {
        _clickedMovie.value = movie
    }

    fun loadingActors(movieId: Int) {
        viewModelScope.launch {
            loadingActorsDb(movieId)
            loadingActorsNet(movieId)
        }
    }

    private suspend fun loadingActorsNet(movieId: Int) {
        try {
            val networkActors = loadingActorsApi(movieId = movieId)

            _actors.value = networkActors

            if (!networkActors.isNullOrEmpty()) {
                actorRepository.updateActorsCache(networkActors,  movieId)
            }
        } catch (e: Exception) {
            Log.e(FragmentMoviesListViewModel::class.java.simpleName,
                R.string.error_actors_mesage_Api.toString(), e)
        }
    }

    private suspend fun loadingActorsDb(movieId: Int) {
        try {
            val localActors = withContext(Dispatchers.IO) {
                actorRepository.getAllActors(movieId)
            }

            if (localActors.isNotEmpty()) {
                _actors.value = localActors
            }
        } catch (e: Exception) {
            Log.e(FragmentMoviesListViewModel::class.java.simpleName,
                R.string.error_actors_mesage_Db.toString(), e)
        }
    }

    override suspend fun loadingActorsApi(movieId: Int): List<Actor> =
        withContext(Dispatchers.IO) {
            val actorsResponce = moviesApi.getActors(movieId)
            parceActors(actorsResponce.actors)
        }
}
