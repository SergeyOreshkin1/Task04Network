package com.example.task04network.di

import com.example.task04network.repository.AnimalsRepository
import com.example.task04network.repository.AnimalsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsAnimalsRepository(impl: AnimalsRepositoryImpl): AnimalsRepository
}
