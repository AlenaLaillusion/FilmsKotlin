package com.example.fundamentalskotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fundamentalskotlin.data.Movie


class MainActivity : AppCompatActivity(), ChangeFragment  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragments_container, FragmentMoviesList())
                .commit()
        }
    }

     override fun addFragmentMoviesDetails(movie: Movie) {
         val fragment = FragmentMoviesDetails()
         val args = Bundle()
         args.putParcelable(Movie::class.java.simpleName, movie)
         fragment.setArguments(args)
            supportFragmentManager.beginTransaction()
                .apply {
                    addToBackStack(null)
                    add(R.id.fragments_container, fragment)
                    commit()
                }
        }

        override fun backFragmentMoviesList() {
            supportFragmentManager.popBackStack()
        }
    }

