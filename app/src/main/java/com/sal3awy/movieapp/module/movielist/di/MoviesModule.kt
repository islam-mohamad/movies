package com.sal3awy.movieapp.module.movielist.di

import com.sal3awy.movieapp.module.movielist.data.repository.MoviesRepositoryImpl
import com.sal3awy.movieapp.module.movielist.data.source.remote.MoviesService
import com.sal3awy.movieapp.module.movielist.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class MoviesModule {

    companion object {
        @Provides
        @ViewModelScoped
        fun provideMoviesService(retrofit: Retrofit) = retrofit.create(MoviesService::class.java)
    }

    @Binds
    @ViewModelScoped
    abstract fun bindMoviesRepository(impl: MoviesRepositoryImpl): MoviesRepository
}