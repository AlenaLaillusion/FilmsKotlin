package com.example.fundamentalskotlin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentMoviesList: Fragment()  {

    private var ivMovies: ImageView? = null
    var listener: ClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivMovies = view.findViewById<ImageView>(R.id.iv_mask).apply {
            setOnClickListener { listener?.addFragmentMoviesDetails() }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
            listener = context as? ClickListener

    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}


