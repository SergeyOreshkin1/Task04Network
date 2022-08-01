package com.example.task04network.usecase

import com.example.task04network.repository.AnimalsRepository
import javax.inject.Inject

interface GetDogImagesUseCase {
    suspend operator fun invoke(breed: String, limit: String): List<String>?
}

class GetDogImagesUseCaseImpl @Inject constructor(
    private val repository: AnimalsRepository
) : GetDogImagesUseCase {
    override suspend fun invoke(breed: String, limit: String): List<String>? {
        return repository.getDogImages(breed, limit).body()?.dogImage
    }
}