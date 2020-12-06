package com.example.fundamentalskotlin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fundamentalskotlin.data.Actor
import com.example.fundamentalskotlin.domain.ActorDataSource

class FragmentMoviesDetails: Fragment() {

    private var changeFragment: ChangeFragment? = null
    private lateinit var adapter: ActorsAdapterDiffUtil

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_details, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler: RecyclerView = view.findViewById(R.id.rv_actor)
        adapter = ActorsAdapterDiffUtil()
        recycler?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = adapter

       view.findViewById<TextView>(R.id.tv_cast).setOnClickListener {
           shuffleActors()
        }
        var btnBack: TextView? = null
        btnBack = view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener {
                 changeFragment?.backFragmentMoviesList()
            }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        changeFragment = context  as? ChangeFragment
    }


    override fun onDetach() {
        super.onDetach()
        changeFragment = null
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        adapter.bindActors(ActorDataSource().getActor())
        adapter.notifyDataSetChanged()
    }


    private fun shuffleActors() {
        val originalData: List<Actor> = ActorDataSource().getActor()
        val shuffledList: List<Actor> = ActorDataSource().getActor().shuffled()
        adapter.bindActors(shuffledList)
        /* Update adapter */
        val diffCallback = MovieDiffUtil(originalData, shuffledList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(adapter)
    }

}
