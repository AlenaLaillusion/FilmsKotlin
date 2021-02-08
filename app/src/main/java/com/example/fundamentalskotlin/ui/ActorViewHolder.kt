package com.example.fundamentalskotlin.ui

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fundamentalskotlin.R
import com.example.fundamentalskotlin.data.Actor
import com.example.fundamentalskotlin.databinding.ViewHolderActorBinding


class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ViewHolderActorBinding.bind(itemView)

    fun onBind(actor: Actor) {
        Glide.with(itemView.context)
            .load(actor.picture)
            .apply(imageOption)
            .into(binding.ivActoravatar)

        binding.tvActorname.text = actor.name
        Log.d("Parcel", "actor.name = ${actor.name}")
    }
    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_avatar_placeholder)
            .fallback(R.drawable.ic_avatar_placeholder)
            .centerCrop()
    }
}