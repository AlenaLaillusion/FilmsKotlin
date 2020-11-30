package com.example.fundamentalskotlin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesDetails: Fragment() {

    private var btnBack: TextView? = null
    private var listener: FragmentMoviesList.ClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_details, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBack = view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener {
                 listener?.backFragmentMoviesList()
            }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentMoviesList.ClickListener){
            listener = context
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
