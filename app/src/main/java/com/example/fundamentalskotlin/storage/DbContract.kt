package com.example.fundamentalskotlin.storage

import android.provider.BaseColumns

object DbContract {

    const val DATABASE_NAME = "Movies_db"

    object MovieContract {
        const val TABLE_NAME = "movie"

        const val COLUMN_NAME_ID = BaseColumns._ID
    }

    object ActorContract {
        const val TABLE_NAME = "actor"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_ACTOR_ID = "actor_id"
        const val COLUMN_NAME_IMAGE = "image_url"
        const val COLUMN_NAME_MOVIE_ID = "movie_id"
    }
}