package com.example.fundamentalskotlin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fundamentalskotlin.data.Movie
import com.example.fundamentalskotlin.databinding.ViewHolderMovieBinding

class MoviesAdapter(
    private val clickListener: ClickListener?
) : RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies = listOf<Movie>()


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener { clickListener?.onClick(movies[position]) }
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie, parent, false)
        return MoviesViewHolder(view)
    }
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ViewHolderMovieBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun onBind(movie: Movie) {
        Glide.with(itemView.context)
            .load(movie.poster)
            .apply(imageOption)
            .into(binding.ivMask)

        when {
            movie.adult -> binding.tvRectangle.text =
                itemView.resources.getString(R.string.age_rating_default)
            else -> binding.tvRectangle.visibility = View.INVISIBLE
        }

        binding.tvName.text = movie.title
        binding.tvMinut.text =
            movie.runtime.toString() + itemView.resources.getString(R.string.min)
        binding.tvReviews.text =
            movie.reviews.toString() + itemView.resources.getString(R.string.reviews_)
        binding.tvGenre.text = movie.genres.joinToString(", ") { it.name }
        binding.ratingStar.rating = movie.ratings / RATING_CONST
        binding.ivLike.setImageResource(if (movie.like) R.drawable.ic_like_red else R.drawable.ic_like)
    }

    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_avatar_placeholder)
            .fallback(R.drawable.ic_avatar_placeholder)
            .centerCrop()
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

const val RATING_CONST = 2