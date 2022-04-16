package com.sal3awy.movieapp.module.movielist.data.repository

import com.sal3awy.movieapp.module.movielist.data.model.mapper.toEntity
import com.sal3awy.movieapp.module.movielist.data.source.remote.MoviesService
import com.sal3awy.movieapp.module.movielist.domain.entity.MovieEntity
import com.sal3awy.movieapp.module.movielist.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val service: MoviesService) :
    MoviesRepository {
    override fun getMoviesList(page: Int, limit: Int): Single<List<MovieEntity>> {
        return service.getMoviesList(page, limit).map { response ->
            response.results.map { it.toEntity() }
        }
    }
}