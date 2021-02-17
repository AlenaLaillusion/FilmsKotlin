package com.example.fundamentalskotlin.cache

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fundamentalskotlin.MoviesApp
import com.example.fundamentalskotlin.domain.ActorsEntity
import com.example.fundamentalskotlin.domain.MoviesEntity

@Database(entities = [MoviesEntity::class, ActorsEntity::class], version = 1, exportSchema = false)
abstract class MoviesDataBase : RoomDatabase() {

    abstract val moviesDao: MoviesDao

    companion object {

        val create : MoviesDataBase by lazy {
               Room.databaseBuilder(
                MoviesApp.context(),
                MoviesDataBase::class.java,
                MovieDbContract.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}