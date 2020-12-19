package com.example.fundamentalskotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.data.loadMovies
import com.example.fundamentalskotlin.databinding.FragmentMoviesListBinding
import com.example.fundamentalskotlin.domain.MoviesDataSource
import kotlinx.coroutines.*


class FragmentMoviesList: Fragment() {

    //private var recycler: RecyclerView? = null
    private var changeFragment: ChangeFragment? = null
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d(FragmentMoviesList::class.java.simpleName, "CoroutineException: $throwable")
    }

    private var scope = CoroutineScope(
        SupervisorJob() +
                Dispatchers.IO +
                exceptionHandler
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovie.layoutManager = GridLayoutManager(activity, GRID_LAYOUT_ROW_COUNT)
        binding.rvMovie.adapter = MoviesAdapter(clickListener)
        binding.rvMovie.hasFixedSize()
    }
    
    override fun onStart() {
        updateData()
        super.onStart()
    }

    override fun onAttach(context: Context) {
        changeFragment = context as? ChangeFragment
        super.onAttach(context)
    }

    override fun onDetach() {
        changeFragment = null
        super.onDetach()
    }

    private fun updateData() {
        var moviesList: List<Movie>? = null
        scope.launch {
            // get movie list
            moviesList = loadMovies(requireContext())
            // send list into adapter
            (binding.rvMovie.adapter as? MoviesAdapter)?.apply {
                moviesList?.let { bindMovies(it) }
            }
        }

    }

    private val clickListener = object : ClickListener {
        override fun onClick(movie: Movie) {
            binding.rvMovie.let { rv ->
                Log.d("Parcel", "move.name = ${movie.title}")
                changeFragment?.addFragmentMoviesDetails(movie)
            }
        }
    }

    override fun onDestroyView() {
        scope.cancel()
        _binding = null
        super.onDestroyView()
    }
}
const val  GRID_LAYOUT_ROW_COUNT = 2
