package com.example.fundamentalskotlin.api

import com.example.fundamentalskotlin.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    /**
     *  example: https://api.themoviedb.org/3/genre/movie/list?api_key=8f255b4f53d2df4716159d2fb704d5ea&page=1
     */

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") key: String = BuildConfig.API_KEY): GenresDto

    /**
     *  example: https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&page=1
     */
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1
    ): MoviesDto

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String = BuildConfig.API_KEY
    ): ActorsDto
}