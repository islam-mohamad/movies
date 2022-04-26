package com.sal3awy.movieapp.module.movielist.data.source.remote

import com.sal3awy.movieapp.BuildConfig
import com.sal3awy.movieapp.BuildConfig.API_KEY
import com.sal3awy.movieapp.module.movielist.data.model.MoviesListResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("discover/movie")
    fun getMoviesList(
        @Query("page")page: Int,
        @Query("limit")limit: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Single<MoviesListResponseModel>
}