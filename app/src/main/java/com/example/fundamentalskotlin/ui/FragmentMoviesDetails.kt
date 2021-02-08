package com.example.fundamentalskotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.fundamentalskotlin.R
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.databinding.FragmentMoviesDetailsBinding
import com.example.fundamentalskotlin.presentation.FragmentMoviesDetailsViewModel
import com.example.fundamentalskotlin.presentation.MoviesDetailsViewModelFactory


class FragmentMoviesDetails : Fragment() {

    private lateinit var viewModel: FragmentMoviesDetailsViewModel

    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)

       val movie = FragmentMoviesDetailsArgs.fromBundle(requireArguments()).clickedMovie

        val viewModelFactory = MoviesDetailsViewModelFactory(movie)

        viewModel = ViewModelProvider(this, viewModelFactory).get(FragmentMoviesDetailsViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvActor.adapter =
            ActorsAdapterDiffUtil()
        binding.rvActor.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.tvBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        setObservers()

        viewModel.loadMovie()
    }

    private fun setObservers() {
        viewModel.clickedMovie.observe(viewLifecycleOwner, {
            setMovieData(it)
        })
    }

    private fun setMovieData(movie: Movie) {
        movie.backdrop.let {
            Glide.with(requireContext())
                .load(movie.backdrop)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .apply(imageOption)
                .into(binding.ivOrig)

        }

        with(movie) {

            when {
                adult -> binding.tvRg.text = resources.getString(R.string.age_rating_default)
                else -> binding.tvRg.visibility = View.INVISIBLE
            }

            binding.tvNamef.text = title
            binding.tvReviews.text = reviews.toString() + resources.getString(R.string.reviews_)
            binding.tvTag.text = genres.joinToString(", ") { it.name }
            binding.tvStorylinetxt.text = overview
            binding.ratingStars.rating = (ratings / RATING_CONST)

            when {
                actors.isNotEmpty() -> (binding.rvActor.adapter as? ActorsAdapterDiffUtil)?.bindActors(actors)
                else -> binding.tvCast.visibility = View.INVISIBLE
            }

        }
    }

    val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_placeholder)
        .centerCrop()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}


