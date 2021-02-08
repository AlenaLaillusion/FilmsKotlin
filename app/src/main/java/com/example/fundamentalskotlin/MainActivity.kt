package com.example.fundamentalskotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.databinding.ActivityMainBinding
import com.example.fundamentalskotlin.ui.FragmentMoviesDetails
import com.example.fundamentalskotlin.ui.FragmentMoviesList


class MainActivity : AppCompatActivity(), ChangeFragment {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
           openFragment(FragmentMoviesList(), false)
        }
    }

    override fun addFragmentMovieDetails(movie: Movie) {

        val fragmentMovieDetails = FragmentMoviesDetails()
        val args = Bundle()
        args.putParcelable(Movie::class.java.simpleName, movie)
        fragmentMovieDetails.setArguments(args)
        openFragment(fragmentMovieDetails, true)
    }

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fr_container, fragment)

        if(addToBackStack) {
            transaction.addToBackStack(Fragment::class.java.name)
        }
            transaction.commit()
    }
}

