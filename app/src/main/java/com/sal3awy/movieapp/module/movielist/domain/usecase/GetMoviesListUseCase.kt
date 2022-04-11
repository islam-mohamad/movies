package com.sal3awy.movieapp.module.movielist.domain.usecase

import com.sal3awy.movieapp.module.movielist.domain.entity.MovieEntity
import com.sal3awy.movieapp.module.movielist.domain.entity.exception.InvalidLimitException
import com.sal3awy.movieapp.module.movielist.domain.entity.exception.InvalidPageNumberException
import com.sal3awy.movieapp.module.movielist.domain.entity.param.GetMovieListParam
import com.sal3awy.movieapp.module.movielist.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(private val repository: MoviesRepository) {
    operator fun invoke(param: GetMovieListParam): Single<List<MovieEntity>> {
        return with(param) {
            when {
                page < 1 -> Single.error(InvalidPageNumberException())
                limit in 11 downTo 4 -> Single.error(InvalidLimitException())
                else -> repository.getMoviesList(page, limit)/*.flatMapPublisher {
                    Flowable.just(it)
                }.flatMapIterable {
                    it
                }.map {
                    it
                }.toList()*/
            }
        }
    }
}