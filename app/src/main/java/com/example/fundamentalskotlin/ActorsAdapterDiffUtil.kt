package com.example.fundamentalskotlin

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fundamentalskotlin.data.Actor

class ActorsAdapterDiffUtil : RecyclerView.Adapter<ActorViewHolder>() {

    private var actorsList = listOf<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
        ActorViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_actor, parent, false))

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actorsList[position])
    }

    override fun getItemCount(): Int = actorsList.size

    fun bindActors(newActors: List<Actor>) {
        actorsList = newActors
        notifyDataSetChanged()
    }
}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_avatar)
    private val name: TextView = itemView.findViewById(R.id.tv_actor_name)

    fun onBind(actor: Actor) {
        Glide.with(itemView.context)
            .load(actor.picture)
            .apply(imageOption)
            .into(avatar)

        name.text = actor.name
        Log.d("Parcel", "actor.name = ${actor.name}")
    }

    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_avatar_placeholder)
            .fallback(R.drawable.ic_avatar_placeholder)
            .centerCrop()
    }
}

