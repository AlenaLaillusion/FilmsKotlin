package com.example.fundamentalskotlin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fundamentalskotlin.R
import com.example.fundamentalskotlin.data.Actor

class ActorsAdapterDiffUtil : RecyclerView.Adapter<ActorViewHolder>() {

    private var actorsList = emptyList<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
        ActorViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.view_holder_actor,
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actorsList[position])
    }

    override fun getItemCount(): Int = actorsList.size

    fun bindActors(newActors: List<Actor>) {
        actorsList = newActors
        notifyDataSetChanged()
    }
}

