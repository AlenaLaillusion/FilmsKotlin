package com.example.fundamentalskotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ClickListener {

   // private var moviesListFragment: FragmentMoviesList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .apply {
                    //addToBackStack(null)
                    add(R.id.fragments_container, FragmentMoviesList()!!, MOVIE_LIST_FRAGMENT)
                    commit()
                }
        } else {

                supportFragmentManager.findFragmentByTag(MOVIE_LIST_FRAGMENT) as? FragmentMoviesList
        }
    }

    override fun addFragmentMoviesDetails() {
        supportFragmentManager.beginTransaction()
            .apply {
                addToBackStack(null)
            add(R.id.fragments_container, FragmentMoviesDetails())
                commit()
        }
    }

    override fun backFragmentMoviesList() {
        supportFragmentManager.popBackStack()

    }
    companion object {
        private const val MOVIE_LIST_FRAGMENT = "FragmentMoviesList"
    }
}
