package com.example.fundamentalskotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.databinding.FragmentMoviesListBinding
import com.example.fundamentalskotlin.domain.State
import com.example.fundamentalskotlin.presentation.FragmentMoviesListViewModel
import com.example.fundamentalskotlin.presentation.MoviesListViewModelFactory


class FragmentMoviesList : Fragment() {

    private val viewModel: FragmentMoviesListViewModel by viewModels { MoviesListViewModelFactory() }

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovie.adapter =
            MoviesAdapter(
                ClickListener { movie ->
                    openMoviesDetailFragment(movie)
                })
        binding.rvMovie.layoutManager = GridLayoutManager(context,
            GRID_LAYOUT_ROW_COUNT
        )

        setObservers()

        if(viewModel.moviesData.value.isNullOrEmpty()) {
            viewModel.loadMovies()
        }
    }

    private fun openMoviesDetailFragment(movie: Movie) {
        this.findNavController().navigate(
            FragmentMoviesListDirections.actionToMoviesDetails(
                movie
            )
        )
    }

    private fun setObservers() {

        viewModel.moviesData.observe(viewLifecycleOwner, { movieList ->
            (binding.rvMovie.adapter as MoviesAdapter).apply {
                bindMovies(movieList)
            }
        })

        viewModel.state.observe(viewLifecycleOwner, { status ->
            when (status) {
                is State.Init, is State.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                }
                is State.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is State.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

const val GRID_LAYOUT_ROW_COUNT = 2
