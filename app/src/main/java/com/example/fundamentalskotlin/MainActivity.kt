package com.example.fundamentalskotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ChangeFragment  {

   // private var moviesListFragment: FragmentMoviesList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .apply {
                    //addToBackStack(null)
                    add(R.id.fragments_container, FragmentMoviesList.newInstance())
                    commit()
                }
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
    }

