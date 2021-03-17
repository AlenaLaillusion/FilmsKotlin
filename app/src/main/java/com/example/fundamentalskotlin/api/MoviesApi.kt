package com.example.fundamentalskotlin.api

import com.example.fundamentalskotlin.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    //https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
    //https://api.themoviedb.org/3/movie/now_playing?api_key=<<api_key>>&language=en-US&page=1
    @GET("movie/now_playing")
    suspend fun getMovies (
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "ru-US",
        @Query("page") page: Int = 1
    ): MoviesResponce

    // https://api.themoviedb.org/3/genre/movie/list?api_key=<<api_key>>&language=en-US
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "ru-US"
    ): GenresResponce

    //https://api.themoviedb.org/3/movie/{movie_id}/credits?api_key=<<api_key>>&language=en-US
    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "ru-US"
    ): ActorsResponce
}