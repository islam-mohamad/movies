package com.sal3awy.movieapp.module.movielist.data.model

import com.google.gson.annotations.SerializedName
import com.sal3awy.movieapp.module.movielist.domain.entity.MovieEntity

data class MoviesListResponseModel(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<MovieModel> = arrayListOf(),
    @SerializedName("total_results") var totalResults: Int? = null,
    @SerializedName("total_pages") var totalPages: Int? = null
)