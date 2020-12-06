package com.example.fundamentalskotlin.domain

import com.example.fundamentalskotlin.data.Actor

class ActorDataSource {
    fun getActor(): List<Actor> {
        return listOf(
            Actor("https://image.tmdb.org/t/p/w600_and_h900_bestv2/im9SAqJPZKEbVZGmjXuLI4O7RvM.jpg",
            "Robert Downey Jr."),
            Actor("https://image.tmdb.org/t/p/w600_and_h900_bestv2/AuC5PNfU0nnhiC9BQlmy9qg9WIc.jpg",
            "Chris Evans"),
            Actor("https://image.tmdb.org/t/p/w600_and_h900_bestv2/5rw7hsmilbNSgYTxHZ8k7DYWdIY.jpg",
            "Mark Ruffalo"),
            Actor("https://image.tmdb.org/t/p/w600_and_h900_bestv2/rJJ6NmzKv3Tt364RpVOSDhI9U8V.jpg",
            "Chris Hemsworth"),
            Actor("https://image.tmdb.org/t/p/w600_and_h900_bestv2/opEemSnjnN4hbwymHz5P0VVuk4F.jpg",
            "Scarlett Johansson"),
            Actor("https://image.tmdb.org/t/p/w600_and_h900_bestv2/ycFVAVMliCCf0zXsKWNLBG3YxzK.jpg",
            "Jeremy Renner")
        )
    }
}