package com.example.fundamentalskotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fundamentalskotlin.data.Movie

class MoviesAdapter(private val clickListener: ClickListener?
): RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies = listOf<Movie>()

    override fun getItemViewType(position: Int): Int {
        return when (movies.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_ACTORS

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movies_empty, parent, false))
            else -> DataViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false))
        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                holder.onBind(movies[position])
                holder.itemView.setOnClickListener {
                    clickListener?.onClick(movies[position])
                }
            }
            is EmptyViewHolder -> { /* nothing to bind */ }
        }
    }
}

abstract class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class EmptyViewHolder(itemView: View) : MoviesViewHolder(itemView)

private class DataViewHolder(itemView: View) : MoviesViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.iv_mask)
    private val name: TextView = itemView.findViewById(R.id.tv_name)
    private val ageRating: TextView = itemView.findViewById(R.id.tv_rectangle)
    private val reviews: TextView = itemView.findViewById(R.id.tv_reviews)
    private val genre: TextView = itemView.findViewById(R.id.tv_genre)
    private val minut: TextView = itemView.findViewById(R.id.tv_minut)

    fun onBind(movie: Movie) {
        Glide.with(context)
            .load(movie.image)
            .apply(imageOption)
            .into(image)

        name.text = movie.name
        ageRating.text = movie.ageRating
        reviews.text = movie.reviews
        genre.text = movie.genre
        minut.text = movie.minut
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

private const val VIEW_TYPE_EMPTY = 0
private const val VIEW_TYPE_ACTORS = 1