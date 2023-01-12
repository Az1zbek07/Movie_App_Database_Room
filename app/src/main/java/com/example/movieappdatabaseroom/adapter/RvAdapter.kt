package com.example.movieappdatabaseroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappdatabaseroom.database.MovieDatabase
import com.example.movieappdatabaseroom.database.MovieEntity
import com.example.movieappdatabaseroom.databinding.ItemLayoutBinding

class RvAdapter(private val context: Context): RecyclerView.Adapter<RvAdapter.MovieViewHolder>() {
    private val db by lazy { MovieDatabase(context) }
    lateinit var onClick: (MovieEntity) -> Unit
    lateinit var editBtn: (MovieEntity) -> Unit
    var movieList = mutableListOf<MovieEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movieEntity: MovieEntity){
            binding.itemAuthor.text = movieEntity.author
            binding.itemName.text = movieEntity.name
            binding.itemTime.text = movieEntity.date
            binding.btnDelete.setOnClickListener {
                db.dao.deleteMovie(movieEntity)
                notifyDataSetChanged()
            }
            binding.btnEdit.setOnClickListener {
                editBtn(movieEntity)
            }
            itemView.setOnClickListener {
                onClick(movieEntity)
            }
        }
    }

    fun setList(movieList: MutableList<MovieEntity>){
        this.movieList = movieList
        notifyDataSetChanged()
    }
}