package com.example.fundamentalskotlin

import com.example.fundamentalskotlin.data.Movie

interface ChangeFragment {

    fun addFragmentMoviesDetails(movie: Movie)
    fun backFragmentMoviesList()
}