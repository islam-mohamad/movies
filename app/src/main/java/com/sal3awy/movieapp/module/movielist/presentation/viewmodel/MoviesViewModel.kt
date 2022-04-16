package com.sal3awy.movieapp.module.movielist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sal3awy.movieapp.R
import com.sal3awy.movieapp.core.base.BaseViewModel
import com.sal3awy.movieapp.module.movielist.domain.entity.MovieEntity
import com.sal3awy.movieapp.module.movielist.domain.entity.exception.InvalidLimitException
import com.sal3awy.movieapp.module.movielist.domain.entity.exception.InvalidPageNumberException
import com.sal3awy.movieapp.module.movielist.domain.entity.param.GetMovieListParam
import com.sal3awy.movieapp.module.movielist.domain.usecase.GetMoviesListUseCase
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo

class MoviesViewModel(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val mainScheduler: Scheduler,
    private val ioScheduler: Scheduler
) : BaseViewModel() {

    //TODO use single live event
    val moviesListLiveData = MutableLiveData<List<MovieEntity>>()
    val errorLiveData = MutableLiveData<Int>()

    fun getMoviesList(page: Int, limit: Int) {
        getMoviesListUseCase(GetMovieListParam(page, limit))
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe(::onGetMoviesSuccess, ::onError)
            .addTo(compositDisposable)
    }

    private fun onGetMoviesSuccess(movies: List<MovieEntity>) {
        moviesListLiveData.value = movies
    }

    //TODO Map Backend Domain Exception
    private fun onError(error: Throwable) {
        errorLiveData.value = when (error) {
            is InvalidLimitException -> R.string.invalid_limit
            is InvalidPageNumberException -> R.string.invalid_page
            else -> R.string.wrong_msg
        }
    }
}