package com.leary.marvelheroesapp.di

import com.leary.marvelheroesapp.data.repositories.HeroRepositoryImpl
import com.leary.marvelheroesapp.data.repositories.HeroRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsHeroRepository(heroRepositoryImpl: HeroRepositoryImpl): HeroRepository
}