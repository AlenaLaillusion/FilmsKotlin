package com.example.fundamentalskotlin.api

import com.example.fundamentalskotlin.BuildConfig
import com.example.fundamentalskotlin.data.Actor
import com.example.fundamentalskotlin.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun parceMovie (
    moviesResponce: List<MovieResponce>,
    genres: List<GenreResponce>) : List<Movie> = withContext(Dispatchers.Default) {
    val genresMap: Map<Int, GenreResponce> = genres.associateBy { it.id }

    moviesResponce.map {movieResponce: MovieResponce ->
        Movie(
            id = movieResponce.id,
            title = movieResponce.title,
            overview = movieResponce.overview,
            poster = movieResponce.poster.let { BuildConfig.IMAGE_URL + movieResponce.poster },
            backdrop = movieResponce.backdrop.let { BuildConfig.IMAGE_URL + movieResponce.backdrop },
            ratings = movieResponce.ratings,
            adult = movieResponce.adult,
            runtime = movieResponce.runtime,
            reviews = movieResponce.reviews,
            genres = movieResponce.genreIDS.map{ genresMap[it]?.name.toString()}
        )
    }
}

suspend fun parceActors (actorsResponce: List<ActorResponce>): List<Actor> = withContext(Dispatchers.Default) {

    actorsResponce.map { actorResponce: ActorResponce ->
        Actor(
            id = actorResponce.id,
            name = actorResponce.name,
            picture = actorResponce.picture.let { BuildConfig.IMAGE_URL + actorResponce.picture }
        )
    }

}