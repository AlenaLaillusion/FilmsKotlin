package com.example.fundamentalskotlin.storage

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fundamentalskotlin.App
import com.example.fundamentalskotlin.storage.entitys.ActorEntity
import com.example.fundamentalskotlin.storage.entitys.MovieEntity

@Database(entities = [MovieEntity::class, ActorEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun actorsDao(): ActorsDao

    companion object {
        val instance: MoviesDatabase by lazy {
            Room.databaseBuilder(
                App.context(),
                MoviesDatabase::class.java,
                DbContract.DATABASE_NAME
            ).build()
        }
    }
}