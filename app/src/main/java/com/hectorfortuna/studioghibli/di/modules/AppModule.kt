package com.hectorfortuna.studioghibli.di.modules

import com.hectorfortuna.studioghibli.data.repository.FilmsRepository
import com.hectorfortuna.studioghibli.data.repository.FilmsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindFilmRepository(
        filmsRepository: FilmsRepositoryImpl
    ): FilmsRepository
}