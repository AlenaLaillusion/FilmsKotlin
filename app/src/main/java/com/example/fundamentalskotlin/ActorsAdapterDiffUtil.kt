package com.example.fundamentalskotlin

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

    private var actors: List<Actor> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
    }
}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_avatar_placeholder)
            .fallback(R.drawable.ic_avatar_placeholder)
            .centerCrop()
    }

    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_avatar)
    private val nameActor: TextView = itemView.findViewById(R.id.tv_actor_name)

    fun onBind(actor: Actor) {
        Glide.with(context)
            .load(actor.avatar)
            .apply(imageOption)
            .into(avatar)
        nameActor.text = actor.nameActor

    }
}


private val RecyclerView.ViewHolder.context
    get() = this.itemView.context