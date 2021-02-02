package com.example.fundamentalskotlin.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamentalskotlin.R
import com.example.fundamentalskotlin.api.MovieApi
import com.example.fundamentalskotlin.api.convertMovieDtoToDomain
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.storage.repository.MoviesRepository
import kotlinx.coroutines.launch

class FragmentMoviesListViewModel(
    private val apiService: MovieApi,
    private val repository: MoviesRepository
) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Init)
    val state: LiveData<State>get() = _state

    private val _moviesData = MutableLiveData<List<Movie>>()
    val moviesData: LiveData<List<Movie>> get() = _moviesData


    fun loadMovies() {
        viewModelScope.launch {
            _state.value = State.Loading
            loadMoviesFromDb()
            loadMoviesFromApi()
        }
    }

    private suspend fun loadMoviesFromApi() {
        try {

            if (state.value != State.Success) {
                _state.value = State.Loading
        }

            val genres = apiService.getGenres()
            val moviesDto = apiService.getMovies()
            val movies = convertMovieDtoToDomain(moviesDto.results, genres.genres)

            _moviesData.value = movies
            _state.value = State.Success

            if (!movies.isNullOrEmpty()) {
                repository.rewriteMoviesListIntoDB(movies)
            }
    } catch (e: Exception) {
            if (state.value != State.Success) {
                _state.value = State.Error
            }
            Log.e(FragmentMoviesListViewModel::class.java.simpleName,
                "Error grab movies data from API: ${e.message}")
        }
    }

    private suspend fun loadMoviesFromDb() {
        try {
            val moviesDb = repository.getAllMovies()

            if (moviesDb.isNotEmpty()) {
                _moviesData.value = moviesDb
                _state.value = State.Success
            }
        } catch (e: java.lang.Exception) {
            Log.e(FragmentMoviesListViewModel::class.java.simpleName,
            R.string.error_mesage.toString() + {e.message})
        }
    }
}