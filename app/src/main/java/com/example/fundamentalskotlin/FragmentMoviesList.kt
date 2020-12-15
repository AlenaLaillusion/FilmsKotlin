package com.example.fundamentalskotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.domain.MoviesDataSource


class FragmentMoviesList: Fragment() {

    private var recycler: RecyclerView? = null
    private var changeFragment: ChangeFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rv_movie)
        recycler?.layoutManager = GridLayoutManager(activity, GRID_LAYOUT_ROW_COUNT)
        recycler?.adapter = MoviesAdapter(clickListener)
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
        (recycler?.adapter as? MoviesAdapter)?.
            bindMovies(MoviesDataSource().getMovies())
    }

    private val clickListener = object : ClickListener {
        override fun onClick(movie: Movie) {
            recycler?.let { rv ->
                Log.d("Parcel", "move.name = ${movie.name}")
                changeFragment?.addFragmentMoviesDetails(movie)
            }
        }
    }
}
const val  GRID_LAYOUT_ROW_COUNT = 2
