package com.sal3awy.movieapp.module.movielist.data.model.mapper

import com.sal3awy.movieapp.module.movielist.data.model.MovieModel
import com.sal3awy.movieapp.module.movielist.domain.entity.MovieEntity

fun MovieModel.toEntity() = MovieEntity(
    posterPath = posterPath,
    adult = adult,
    overview = overview,
    releaseDate = releaseDate,
    genreIds = genreIds,
    id = id,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    title = title,
    backdropPath = backdropPath,
    popularity = popularity,
    voteCount = voteCount,
    video = video,
    voteAverage = voteAverage
)