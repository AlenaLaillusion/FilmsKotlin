package com.example.fundamentalskotlin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fundamentalskotlin.R
import com.example.fundamentalskotlin.data.Movie

class MoviesAdapter(
    private val clickListener: ClickListener
) : RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies = emptyList<Movie>()


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener { clickListener.onClick(movies[position]) }
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

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

const val RATING_CONST = 2

class ClickListener(val clickListener: (movie: Movie) -> Unit) {
    fun onClick(movie: Movie) = clickListener(movie)
}