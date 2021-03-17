package com.example.fundamentalskotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.databinding.ActivityMainBinding
import com.example.fundamentalskotlin.service.MoviesWorkRepository
import com.example.fundamentalskotlin.ui.FragmentMoviesDetails
import com.example.fundamentalskotlin.ui.FragmentMoviesList
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), ChangeFragment {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
           openFragment(FragmentMoviesList(), false)
            intent?.let(::handleIntent)
        }

        startPeriodicWorkRequest()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
        }
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val id = intent.data?.lastPathSegment
                val movieId = id?.split('?')?.getOrNull(0)?.toIntOrNull()

                Log.d(MainActivity::class.java.simpleName, "movieId =  ${movieId}")
                movieId.let {
                    openFragmentMovieTop(movieId!!)
                    }
                }
            }
        }

    private fun startPeriodicWorkRequest() {
        val constrains = Constraints.Builder()
             .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val work = PeriodicWorkRequestBuilder<MoviesWorkRepository>(UPDATE, TimeUnit.MINUTES)
            .setConstraints(constrains)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(work)

        WorkManager.getInstance(applicationContext)
            .getWorkInfoByIdLiveData(work.id)
            .observe(this,  Observer { workInfo ->
                Log.d("doWork", "changed: ${workInfo.state}")
            })
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

   fun openFragmentMovieTop(movieId: Int){
        val fragmentMovieDetails = FragmentMoviesDetails()
        val args = Bundle()
        args.putInt(MOVIE_ID, movieId)
        fragmentMovieDetails.setArguments(args)
        openFragment(fragmentMovieDetails, true)
    }

    companion object {
        const val UPDATE = 20L
        const val MOVIE_ID = "movie_id"

    }
}

