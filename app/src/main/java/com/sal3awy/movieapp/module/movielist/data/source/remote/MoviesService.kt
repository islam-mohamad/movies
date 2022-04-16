package com.sal3awy.movieapp.module.movielist.data.source.remote

import com.sal3awy.movieapp.module.movielist.data.model.MoviesListResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface MoviesService {
    @GET("")
    fun getMoviesList(page:Int, limit:Int): Single<MoviesListResponseModel>
}