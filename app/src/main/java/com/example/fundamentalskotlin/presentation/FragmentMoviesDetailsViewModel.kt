package com.example.fundamentalskotlin.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamentalskotlin.R
import com.example.fundamentalskotlin.api.ActorsLoading
import com.example.fundamentalskotlin.api.MovieTopLoading
import com.example.fundamentalskotlin.api.MoviesApi
import com.example.fundamentalskotlin.cache.ActorsRepositoryImpl
import com.example.fundamentalskotlin.cache.MovieRepositoryImpl
import com.example.fundamentalskotlin.data.Actor
import com.example.fundamentalskotlin.data.Movie
import kotlinx.coroutines.launch


class FragmentMoviesDetailsViewModel(
    private val movie: Movie?,
    private val moviesApi: MoviesApi,
    private val actorRepository: ActorsRepositoryImpl,
    private val movieRepository: MovieRepositoryImpl,
    private val actorsLoading: ActorsLoading,
    private val movieTopLoading: MovieTopLoading
) : ViewModel() {

    private val _clickedMovie = MutableLiveData<Movie>()
    val clickedMovie: LiveData<Movie> get() = _clickedMovie

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> get() = _actors

    private val _movieIdTop = MutableLiveData<Movie>()
    val movieIdTop: LiveData<Movie> get() = _movieIdTop

       fun loadMovie() {
        _clickedMovie.value = movie
    }

    fun loadMovieId(movieId: Int) {
        viewModelScope.launch {
            loadingMovieId(movieId)
        }
    }

    fun loadingActors(movieId: Int) {
        viewModelScope.launch {
            loadingActorsDb(movieId)
            loadingActorsNet(movieId)
        }
    }

    private suspend fun loadingActorsNet(movieId: Int) {
        try {
            val networkActors = actorsLoading.loadingActorsApi(movieId = movieId)

            _actors.value = networkActors

            if (!networkActors.isNullOrEmpty()) {
                actorRepository.updateActorsCache(networkActors,  movieId)
            }
        } catch (e: Exception) {
            Log.e(FragmentMoviesListViewModel::class.java.simpleName,
                R.string.error_actors_mesage_Api.toString() + e.message)
        }
    }

    private suspend fun loadingActorsDb(movieId: Int) {
        try {
            val localActors = actorsLoading.loadActorsDb(movieId)

            if (localActors.isNotEmpty()) {
                _actors.value = localActors
            }
        } catch (e: Exception) {
            Log.e(FragmentMoviesListViewModel::class.java.simpleName,
                R.string.error_actors_mesage_Db.toString() + e.message)
        }
    }

    private suspend fun loadingMovieId(movieId: Int) {
        try {
            val localMovieId = movieTopLoading.loadingMovieTop(movieId)

            _movieIdTop.value = localMovieId

        } catch (e: Exception) {
            Log.e(FragmentMoviesListViewModel::class.java.simpleName,
                " Error loading movieTop from Db + ${e.message}")
        }
    }
}
