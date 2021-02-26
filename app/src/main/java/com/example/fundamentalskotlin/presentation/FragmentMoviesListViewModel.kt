package com.example.fundamentalskotlin.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamentalskotlin.R
import com.example.fundamentalskotlin.api.MoviesApi
import com.example.fundamentalskotlin.api.MoviesLoading
import com.example.fundamentalskotlin.api.parceMovie
import com.example.fundamentalskotlin.cache.MovieRepositoryImpl
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.domain.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMoviesListViewModel(private val moviesApi: MoviesApi,
                                  private val repositoryImpl: MovieRepositoryImpl
                                  ) : ViewModel(), MoviesLoading {

    private val _state = MutableLiveData<State>(State.Init())
    val state: LiveData<State>get() = _state

    private val _moviesData = MutableLiveData<List<Movie>>()
    val moviesData: LiveData<List<Movie>> get() = _moviesData

    private val _clickedMovie = MutableLiveData<Movie>()
    val clickedMovie: LiveData<Movie> get() = _clickedMovie


    fun loadMovies() {
        viewModelScope.launch {
            _state.value = State.Loading()
            loadingMoviesCatch()
            loadingMoviesNet()
        }
    }

  suspend fun loadingMoviesNet() {
      try{
      val networkMovie = loadingMoviesApi()

       _moviesData.value = networkMovie
       _state.value = State.Success()

       if (!networkMovie.isNullOrEmpty()) {
           repositoryImpl.updateMoviesCache(networkMovie)
       }
            } catch (e: Exception) {
           if (state.value != State.Success()) {
               _state.value = State.Error()
           }
                Log.e(FragmentMoviesListViewModel::class.java.simpleName,
                    R.string.error_movies_mesage_Api.toString(), e)
            }
        }

    suspend fun loadingMoviesCatch() {
       try {
           val localMovie =  withContext(Dispatchers.IO) {
               repositoryImpl.getAllMovies()
           }

           if (localMovie.isNotEmpty()) {
               _moviesData.value = localMovie
               _state.value = State.Success()
           }
    } catch (e: Exception) {
           if (state.value != State.Success()) {
               _state.value = State.Error()
           }
           Log.e(FragmentMoviesListViewModel::class.java.simpleName,
               R.string.error_movies_mesage_Db.toString(), e)
       }
   }

    override suspend fun loadingMoviesApi()=
        withContext(Dispatchers.IO) {
            val moviesResponce = moviesApi.getMovies()
            val genresResponce = moviesApi.getGenres()
            parceMovie(moviesResponce.results, genresResponce.genres)
        }



    fun clickedMovie (movie: Movie) {
        _clickedMovie.value = movie

    }

    fun clickedMovieShow() {
        _clickedMovie.value = null
    }
}