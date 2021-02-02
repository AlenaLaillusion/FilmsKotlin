package com.example.fundamentalskotlin.storage.repository

object RepositoryHolder {

    private val actorsRepository by lazy { ActorsRepositoryImpl() }
    fun actorsRepository(): ActorsRepositoryImpl = actorsRepository

    private val moviesRepository by lazy { MoviesRepositoryImpl() }
    fun moviesRepository(): MoviesRepositoryImpl = moviesRepository
}