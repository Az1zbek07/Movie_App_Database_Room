package com.example.movieappdatabaseroom.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movieappdatabaseroom.MainActivity
import com.example.movieappdatabaseroom.R
import com.example.movieappdatabaseroom.database.MovieDatabase
import com.example.movieappdatabaseroom.database.MovieEntity
import com.example.movieappdatabaseroom.databinding.FragmentAddEditBinding
import com.example.movieappdatabaseroom.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar

class AddEditFragment : Fragment(R.layout.fragment_add_edit) {
    private val db by lazy { MovieDatabase(requireContext()) }
    private var movie: MovieEntity? = null
    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable("movie")
        if (movie == null){
            (activity as MainActivity).supportActionBar?.title = "Create movie"
        }else{
            (activity as MainActivity).supportActionBar?.title = "Edit movie"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddEditBinding.bind(view)
        initViews()
    }

    private fun initViews(){
        if (movie == null){
            addMovie()
        }else{
            editMovie()
        }
    }

    private fun addMovie(){
        binding.btnSave.setOnClickListener {
            val name = binding.editName.text.toString().trim()
            val authors = binding.editAuthor.text.toString().trim()
            val about = binding.editAbout.text.toString().trim()
            val date = binding.editDate.text.toString().trim()
            if (name.isNotBlank() && authors.isNotBlank() && date.isNotBlank()){
                movie = MovieEntity(name = name, author = authors, about = about, date = date)
                db.dao.saveMovie(movie!!)
                findNavController().popBackStack()
                Snackbar.make(requireView(), "Created new movie", Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(requireView(), "Please enter values", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun editMovie(){
        binding.editName.setText(movie?.name)
        binding.editAbout.setText(movie?.about)
        binding.editAuthor.setText(movie?.author)
        binding.editDate.setText(movie?.date)
        binding.btnSave.text = "Edit"

        binding.btnSave.setOnClickListener {
            val name = binding.editName.text.toString().trim()
            val authors = binding.editAuthor.text.toString().trim()
            val about = binding.editAbout.text.toString().trim()
            val date = binding.editDate.text.toString().trim()
            if (name.isNotBlank() && authors.isNotBlank() && date.isNotBlank()){
                movie = MovieEntity(name = name, author = authors, about = about, date = date)
                db.dao.updateMovie(movie!!)
                findNavController().popBackStack()
                Snackbar.make(requireView(), "Movie edited", Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(requireView(), "Please enter values", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}