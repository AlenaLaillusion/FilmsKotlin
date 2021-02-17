package com.example.fundamentalskotlin.cache

import android.provider.BaseColumns

object MovieDbContract {

    const val DATABASE_NAME = "Movies_Db"

    object MovieContract {
        const val TABLE_NAME = "movies"

        const val COLLUMN_NAME_ID = BaseColumns._ID
    }

    object ActorsContract {
        const val TABLE_NAME = "actors"

        const val COLLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_ACTOR_ID = "actor_id"
        const val COLUMN_NAME_IMAGE = "image"
        const val COLUMN_NAME_MOVIE_ID = "movie_id"
    }
}