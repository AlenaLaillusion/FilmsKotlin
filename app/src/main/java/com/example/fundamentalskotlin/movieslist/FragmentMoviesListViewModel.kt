package com.example.fundamentalskotlin.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamentalskotlin.api.MovieApi
import com.example.fundamentalskotlin.api.convertMovieDtoToDomain
import com.example.fundamentalskotlin.data.Movie
import kotlinx.coroutines.launch

class FragmentMoviesListViewModel(private val apiService: MovieApi) : ViewModel() {

    private val _state = MutableLiveData<State>(
        State.Init()
    )
    val state: LiveData<State>get() = _state

    private val _moviesData = MutableLiveData<List<Movie>>()
    val moviesData: LiveData<List<Movie>> get() = _moviesData


    fun loadMovies() {
        viewModelScope.launch {
            try {
                _state.value =
                    State.Loading()

                val genres = apiService.getGenres()

                val moviesDto = apiService.getMovies()

                val movies = convertMovieDtoToDomain(moviesDto.results, genres.genres)

                _moviesData.value = movies
                _state.value =
                    State.Success()
            } catch (e: Exception) {
                _state.value =
                    State.Error()
                Log.e(FragmentMoviesListViewModel::class.java.simpleName, "Error grab movies data ${e.message}")
            }
        }
    }
}