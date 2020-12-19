package com.example.fundamentalskotlin

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.databinding.FragmentMoviesDetailsBinding

class FragmentMoviesDetails: Fragment() {

    private var changeFragment: ChangeFragment? = null

    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvActor.adapter = ActorsAdapterDiffUtil()
       binding.rvActor.layoutManager =
           LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)


        var btnBack: TextView? = null
        btnBack = view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener {
                changeFragment?.backFragmentMoviesList()
            }
        }

            val movie = requireArguments().getParcelable<Movie>(Movie::class.java.simpleName)
            movie?.let { setMovieData(it) }
    }

        @SuppressLint("SetTextI18n")
        private fun setMovieData (movie: Movie) {
            Glide.with(requireContext())
                .load(movie.backdrop)
                .apply(imageOption)
                .into(binding.ivOrig)
            with(movie) {
            when {
                adult -> binding.tvRg.text = resources.getString(R.string.age_rating_default)
                else -> binding.tvRg.visibility = View.INVISIBLE
            }
            binding.tvNamef.text = title
            binding.tvReviews.text = reviews.toString() + "  " +  resources.getString(R.string.reviews_)
            binding.tvTag.text =  genres.joinToString(", ") { it.name }
            binding.tvStorylinetxt.text = overview
            binding.ratingStars.rating = ratings / RATING_CONST

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

        override fun onAttach(context: Context) {
            super.onAttach(context)
            changeFragment = context as? ChangeFragment
        }

        override fun onDetach() {
            super.onDetach()
            changeFragment = null
        }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

