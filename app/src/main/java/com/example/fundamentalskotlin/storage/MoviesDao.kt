package com.example.fundamentalskotlin.storage

import androidx.room.*
import com.example.fundamentalskotlin.storage.entitys.MovieEntity

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Update
    suspend fun update(movie: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Query("DELETE FROM MOVIE")
    suspend fun deleteAll()

    @Query("SELECT * FROM MOVIE ORDER BY _id ASC")
    suspend fun getAll(): List<MovieEntity>
}