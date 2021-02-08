package com.example.fundamentalskotlin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundamentalskotlin.data.Movie

class FragmentMoviesDetailsViewModel(
    private val movie: Movie
) : ViewModel() {

    private val _clickedMovie = MutableLiveData<Movie>()
    val clickedMovie: LiveData<Movie> get() = _clickedMovie

       fun loadMovie() {
        _clickedMovie.value = movie
    }
}