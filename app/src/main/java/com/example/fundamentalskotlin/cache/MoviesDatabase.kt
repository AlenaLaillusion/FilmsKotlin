package com.example.fundamentalskotlin.cache

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fundamentalskotlin.App
import com.example.fundamentalskotlin.domain.ActorEntity
import com.example.fundamentalskotlin.domain.MovieEntity

@Database(entities = [MovieEntity::class, ActorEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract val moviesDao: MoviesDao
    abstract val actorsDao: ActorsDao

    companion object {
        val create: MoviesDatabase by lazy {
            Room.databaseBuilder(
                App.context(),
                MoviesDatabase::class.java,
                DbContract.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}