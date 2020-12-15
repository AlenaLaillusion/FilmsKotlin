package com.example.fundamentalskotlin.domain

import com.example.fundamentalskotlin.data.Movie

class MoviesDataSource {
    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                "https://image.tmdb.org/t/p/original/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
                "13+",
                "125 Reviews",
                "Avengers: End Game",
                "137 min",
                "Adventure, Science Fiction, Action",
                "After the devastating events " +
                        "of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, " +
                        "the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."
            ),
            Movie(
                "https://image.tmdb.org/t/p/original/k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                "16+",
                "98 Reviews",
                "Tenet",
                "97 min",
                "Action, Thriller, Science Fiction",
                "Armed with only one word - Tenet - and fighting for the survival of the entire world, " +
                        "the Protagonist journeys through a twilight world of international espionage on a mission that will unfold " +
                        "in something beyond real time."
            ),
            Movie(
                "https://image.tmdb.org/t/p/original/nh7jITvLdCPyo7gM9PSZdhiVYPH.jpg",
                "13+",
                "38 Reviews",
                "Black Widow",
                "102 min",
                "Action, Drama, Adventure",
                "Natasha Romanoff, also known as Black Widow, confronts the darker parts of her ledger when a dangerous conspiracy " +
                        "with ties to her past arises. Pursued by a force that will stop at nothing to bring her down, Natasha must deal with " +
                        "her history as a spy and the broken relationships left in her wake long before she became an Avenger."
            ),
            Movie(
                "https://image.tmdb.org/t/p/original/iurbE8nt3xZZ1RYrKh33vM8RMV5.jpg",
                "13+",
                "74 Reviews",
                "Wonder Woman 1984",
                "120 min",
                "Fantasy, Action, Adventure\n",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe" +
                        " by the name of the Cheetah."
            )
        )
    }
}