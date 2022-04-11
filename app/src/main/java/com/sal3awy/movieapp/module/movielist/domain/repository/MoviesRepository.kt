package com.sal3awy.movieapp.module.movielist.domain.repository

import com.sal3awy.movieapp.module.movielist.domain.entity.MovieEntity
import io.reactivex.Single

interface MoviesRepository {
    fun getMoviesList(page:Int, limit:Int): Single<List<MovieEntity>>
}