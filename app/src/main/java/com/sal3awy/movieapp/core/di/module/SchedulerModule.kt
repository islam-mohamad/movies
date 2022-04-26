package com.sal3awy.movieapp.core.di.module

import com.sal3awy.movieapp.core.di.qualifier.IoScheduler
import com.sal3awy.movieapp.core.di.qualifier.MainScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object SchedulerModule {
    @Provides
    @IoScheduler
    fun provideIoScheduler() = Schedulers.io()

    @Provides
    @MainScheduler
    fun provideMainScheduler() = AndroidSchedulers.mainThread()
}