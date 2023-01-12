package com.example.movieappdatabaseroom.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieappdatabaseroom.MainActivity
import com.example.movieappdatabaseroom.R
import com.example.movieappdatabaseroom.adapter.RvAdapter
import com.example.movieappdatabaseroom.database.MovieDatabase
import com.example.movieappdatabaseroom.database.MovieEntity
import com.example.movieappdatabaseroom.databinding.FragmentDetailBinding
import com.example.movieappdatabaseroom.databinding.FragmentMoviesBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private lateinit var movie: MovieEntity
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable<MovieEntity>("movie")!!
        (activity as MainActivity).supportActionBar?.title = movie.name
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        binding.apply {
            movieAuthors.text = movie.author
            movieName.text = movie.name
            movieData.text = movie.date
            movieMessage.text = movie.about
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}