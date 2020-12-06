package com.example.fundamentalskotlin

import android.content.Context
import android.os.Bundle
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
        recycler?.layoutManager = GridLayoutManager(activity, 2)
        recycler?.adapter = MoviesAdapter(clickListener)

    }
    
    override fun onStart() {
        super.onStart()
        updateData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // catch listener from activity
        changeFragment = context as? ChangeFragment
    }

    override fun onDetach() {
        super.onDetach()
        changeFragment = null
    }

    private fun updateData() {
        (recycler?.adapter as? MoviesAdapter)?.apply {
            bindMovies(MoviesDataSource().getMovies())
        }
    }


    private val clickListener = object : ClickListener {
        override fun onClick(movie: Movie) {
            recycler?.let { rv ->
                changeFragment?.addFragmentMoviesDetails()

            }
        }

    }


    companion object {
        fun newInstance() = FragmentMoviesList()
    }

}
