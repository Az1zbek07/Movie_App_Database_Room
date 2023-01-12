package com.example.movieappdatabaseroom.database

import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM MOVIETABLE ORDER BY id DESC")
    fun getAllMovie(): List<MovieEntity>

    @Update
    fun updateMovie(movieEntity: MovieEntity)

    @Delete
    fun deleteMovie(movieEntity: MovieEntity)
}