package com.example.fundamentalskotlin.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fundamentalskotlin.cache.MovieDbContract

@Entity(tableName = MovieDbContract.MovieContract.TABLE_NAME)
data class MoviesEntity (
    @PrimaryKey
    @ColumnInfo(name = MovieDbContract.MovieContract.COLLUMN_NAME_ID)
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val adult: Boolean,
    val runtime: Int?,
    val reviews: Int,
    val genres: String,
    val like: Boolean = false
)