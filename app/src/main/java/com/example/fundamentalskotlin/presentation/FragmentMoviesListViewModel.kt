package com.example.fundamentalskotlin.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamentalskotlin.R
import com.example.fundamentalskotlin.api.MoviesApi
import com.example.fundamentalskotlin.api.parceMovie
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.domain.State
import kotlinx.coroutines.launch

class FragmentMoviesListViewModel(private val moviesApi: MoviesApi) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Init())
    val state: LiveData<State>get() = _state

    private val _moviesData = MutableLiveData<List<Movie>>()
    val moviesData: LiveData<List<Movie>> get() = _moviesData

    private val _clickedMovie = MutableLiveData<Movie>()
    val clickedMovie: LiveData<Movie> get() = _clickedMovie

    init {
        loadingMovies()
    }


   private fun loadingMovies() {
        viewModelScope.launch {
            try {
                _state.value = State.Loading()

                val moviesResponce = moviesApi.getMovies()
                val genresResponce = moviesApi.getGenres()

                val movies = parceMovie(moviesResponce.results, genresResponce.genres)

                _moviesData.value = movies

                _state.value = State.Success()

            } catch (e: Exception) {
                _state.value = State.Error()
                Log.e(
                    FragmentMoviesListViewModel::class.java.simpleName,
                    R.string.error_movies_mesage_Data.toString(), e)
            }
        }
    }

    fun clickedMovie (movie: Movie) {
        _clickedMovie.value = movie

    }

    fun clickedMovieShow() {
        _clickedMovie.value = null
    }
}