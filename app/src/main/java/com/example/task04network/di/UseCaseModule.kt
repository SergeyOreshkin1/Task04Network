package com.example.task04network.di

import com.example.task04network.repository.AnimalsRepository
import com.example.task04network.usecase.GetCatFactsUseCase
import com.example.task04network.usecase.GetCatFactsUseCaseImpl
import com.example.task04network.usecase.GetDogImagesUseCase
import com.example.task04network.usecase.GetDogImagesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun providesGetDogImagesUseCase(
        g: AnimalsRepository
    ): GetDogImagesUseCase =
        GetDogImagesUseCaseImpl(g)

    @Provides
    fun providesGetCatFactsUseCase(
        g: AnimalsRepository
    ): GetCatFactsUseCase =
        GetCatFactsUseCaseImpl(g)
}