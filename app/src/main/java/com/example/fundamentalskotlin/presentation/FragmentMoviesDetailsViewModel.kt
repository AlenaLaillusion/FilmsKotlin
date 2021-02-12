package com.example.fundamentalskotlin.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamentalskotlin.R
import com.example.fundamentalskotlin.api.MoviesApi
import com.example.fundamentalskotlin.api.parceActors
import com.example.fundamentalskotlin.data.Actor
import com.example.fundamentalskotlin.data.Movie
import kotlinx.coroutines.launch

class FragmentMoviesDetailsViewModel(
    private val movie: Movie,
    private val moviesApi: MoviesApi
) : ViewModel() {

    private val _clickedMovie = MutableLiveData<Movie>()
    val clickedMovie: LiveData<Movie> get() = _clickedMovie

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> get() = _actors

       fun loadMovie() {
        _clickedMovie.value = movie
    }

    fun loadingActors(id: Int) {
        viewModelScope.launch {
            try {
                val actorsResponce = moviesApi.getActors(movieId = id)
               val actors = parceActors(actorsResponce.actors)
                _actors.value = actors

            } catch (e: Exception) {
                Log.e(
                    FragmentMoviesListViewModel::class.java.simpleName,
                    R.string.error_movies_mesage_Data.toString() + {e.message})
            }
        }
    }


}