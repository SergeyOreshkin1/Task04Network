package com.example.task04network.usecase

import com.example.task04network.model.catFacts.CatFact
import com.example.task04network.repository.AnimalsRepository
import javax.inject.Inject

interface GetCatFactsUseCase {
    suspend operator fun invoke(max_length: Int, limit: Int): List<CatFact>?
}

class GetCatFactsUseCaseImpl @Inject constructor(
    private val repository: AnimalsRepository
) : GetCatFactsUseCase {
    override suspend fun invoke(max_length: Int, limit: Int): List<CatFact>? {
        return repository.getCatFacts(max_length, limit).body()?.data
    }
}