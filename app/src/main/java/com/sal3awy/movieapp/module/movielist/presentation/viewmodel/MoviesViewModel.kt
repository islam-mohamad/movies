package com.sal3awy.movieapp.module.movielist.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.sal3awy.movieapp.R
import com.sal3awy.movieapp.core.base.BaseViewModel
import com.sal3awy.movieapp.core.di.qualifier.IoScheduler
import com.sal3awy.movieapp.core.di.qualifier.MainScheduler
import com.sal3awy.movieapp.core.livedata.SingleLiveEvent
import com.sal3awy.movieapp.module.movielist.domain.entity.MovieEntity
import com.sal3awy.movieapp.module.movielist.domain.entity.exception.InvalidLimitException
import com.sal3awy.movieapp.module.movielist.domain.entity.exception.InvalidPageNumberException
import com.sal3awy.movieapp.module.movielist.domain.entity.param.GetMovieListParam
import com.sal3awy.movieapp.module.movielist.domain.usecase.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    @MainScheduler private val mainScheduler: Scheduler,
    @IoScheduler private val ioScheduler: Scheduler
) : BaseViewModel() {

    private val _moviesListLiveData = SingleLiveEvent<List<MovieEntity>>()
    fun moviesListLiveData(): LiveData<List<MovieEntity>> = _moviesListLiveData
    private val _errorLiveData = SingleLiveEvent<Int>()
    fun errorLiveData(): LiveData<Int> = _errorLiveData
    private val _loadingLiveData = SingleLiveEvent<Boolean>()
    fun loadingLiveData(): LiveData<Boolean> = _loadingLiveData

    fun getMoviesList(page: Int, limit: Int) {
        getMoviesListUseCase(GetMovieListParam(page, limit))
            .doOnSubscribe { _loadingLiveData.value = true }
            .doOnTerminate { _loadingLiveData.value = false }
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe(::onGetMoviesSuccess, ::onError)
            .addTo(compositDisposable)
    }

    private fun onGetMoviesSuccess(movies: List<MovieEntity>) {
        _moviesListLiveData.value = movies
    }

    //TODO Map Backend Domain Exception
    private fun onError(error: Throwable) {
        _errorLiveData.value = when (error) {
            is InvalidLimitException -> R.string.invalid_limit
            is InvalidPageNumberException -> R.string.invalid_page
            else -> R.string.wrong_msg
        }
    }
}