package com.example.fundamentalskotlin

import androidx.recyclerview.widget.DiffUtil
import com.example.fundamentalskotlin.data.Actor

//test
class MovieDiffUtil(
    private val oldListActor: List<Actor>,
    private val newListActor: List<Actor>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldListActor[oldItemPosition]
        val newItem = newListActor[newItemPosition]
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldListActor[oldItemPosition]
        val newItem = newListActor[newItemPosition]
        return oldItem == newItem
    }

    override fun getOldListSize(): Int = oldListActor.size

    override fun getNewListSize(): Int = newListActor.size
}

