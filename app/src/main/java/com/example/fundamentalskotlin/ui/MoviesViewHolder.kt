package com.example.fundamentalskotlin.ui

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fundamentalskotlin.R
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.databinding.ViewHolderMovieBinding

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ViewHolderMovieBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun onBind(movie: Movie) {
        Glide.with(itemView.context)
            .load(movie.poster)
            .apply(imageOption)
            .into(binding.ivMask)

        when {
            movie.adult -> binding.tvRectangle.text = itemView.resources.getString(R.string.age_rating_default)

            else -> binding.tvRectangle.visibility = View.INVISIBLE
        }

        binding.tvName.text = movie.title
        binding.tvMinut.text = movie.runtime.toString() + itemView.resources.getString(R.string.min)
        binding.tvReviews.text = movie.reviews.toString() + itemView.resources.getString(R.string.reviews_)
        binding.tvGenre.text = movie.genres.joinToString(", ")
        binding.ratingStar.rating = movie.ratings / RATING_CONST

        binding.ivLike.setImageResource(
            if (movie.like)
                R.drawable.ic_like_red
            else
                R.drawable.ic_like)
    }

    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_avatar_placeholder)
            .fallback(R.drawable.ic_avatar_placeholder)
            .centerCrop()
    }
}
