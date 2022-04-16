package com.sal3awy.movieapp.core.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel:ViewModel() {
    protected val compositDisposable = CompositeDisposable()

    override fun onCleared() {
        compositDisposable.clear()
        super.onCleared()
    }
}