package com.example.movieappdatabaseroom.fragments

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieappdatabaseroom.MainActivity
import com.example.movieappdatabaseroom.R
import com.example.movieappdatabaseroom.adapter.RvAdapter
import com.example.movieappdatabaseroom.database.MovieDatabase
import com.example.movieappdatabaseroom.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val rvAdapter by lazy { RvAdapter(requireContext()) }
    private val movieDatabase by lazy { MovieDatabase(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesBinding.bind(view)
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "Movies"
    }

    private fun initViews(){
        binding.rv.adapter = rvAdapter
        rvAdapter.setList(movieDatabase.dao.getAllMovie().toMutableList())
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_moviesFragment_to_addEditFragment)
        }
        rvAdapter.onClick = {
            val bundle = bundleOf("movie" to it)
            findNavController().navigate(R.id.action_moviesFragment_to_detailFragment, bundle)
        }
        rvAdapter.editBtn = {
            val bundle = bundleOf("movie" to it)
            findNavController().navigate(R.id.action_moviesFragment_to_addEditFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}