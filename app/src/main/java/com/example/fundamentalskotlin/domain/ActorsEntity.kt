package com.example.fundamentalskotlin.domain

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.example.fundamentalskotlin.cache.MovieDbContract

@Entity(
    tableName = MovieDbContract.ActorsContract.TABLE_NAME,
    foreignKeys = [ForeignKey(entity = MoviesEntity::class,
        parentColumns = arrayOf(MovieDbContract.MovieContract.COLLUMN_NAME_ID),
        childColumns = arrayOf(MovieDbContract.ActorsContract.COLUMN_NAME_MOVIE_ID),
    onDelete = CASCADE
    )],
    indices = [Index(value = [MovieDbContract.ActorsContract.COLUMN_NAME_MOVIE_ID])]
)

data class ActorsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= MovieDbContract.ActorsContract.COLLUMN_NAME_ID)
    val id: Int?,
    @ColumnInfo(name = MovieDbContract.ActorsContract.COLUMN_NAME_ACTOR_ID)
    val actorId: Int,
    val name: String,
    @ColumnInfo(name = MovieDbContract.ActorsContract.COLUMN_NAME_IMAGE)
    val picture: String,
    @ColumnInfo(name = MovieDbContract.ActorsContract.COLUMN_NAME_MOVIE_ID)
    val movieId: Int
)