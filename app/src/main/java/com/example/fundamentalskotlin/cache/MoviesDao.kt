package com.example.fundamentalskotlin.cache

import androidx.room.*
import com.example.fundamentalskotlin.domain.ActorsEntity
import com.example.fundamentalskotlin.domain.MoviesEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MOVIES ORDER BY movie_id DESC")
    suspend fun getAllMovies(): List<MoviesEntity>

    @Query("SELECT * FROM movies  WHERE movie_id == :movieId")
    suspend fun getMovieId(movieId: Int): MoviesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MoviesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MoviesEntity>)

    @Update
    suspend fun update(movie: MoviesEntity)

    @Delete
    suspend fun delete(movie: MoviesEntity)

    @Query("DELETE FROM MOVIES")
    suspend fun deleteAll()

    @Query("SELECT * FROM ACTORS WHERE movie_id == :movieId ORDER BY _id ASC")
    suspend fun getActorsByMovieId(movieId: Int): List<ActorsEntity>

    @Query("DELETE FROM ACTORS WHERE movie_id == :movieId")
    suspend fun deleteActorsByMovieId(movieId: Int)

    @Query("DELETE FROM ACTORS")
    suspend fun deleteActorsAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActors(actors: List<ActorsEntity> )

}