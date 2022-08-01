package com.example.task04network.repository

import com.example.task04network.api.catAPI.RetrofitCatInstance
import com.example.task04network.api.dogApi.RetrofitDogInstance
import com.example.task04network.model.catFacts.CatFactsResponse
import com.example.task04network.model.dogImages.DogResponse
import retrofit2.Response
import javax.inject.Inject

class AnimalsRepositoryImpl @Inject constructor(): AnimalsRepository {

    override suspend fun getDogImages(breed: String, limit: String): Response<DogResponse> {
         return RetrofitDogInstance.api.getDogsByBreeds(breed, limit)
    }
    override suspend fun getCatFacts(max_length: Int, limit: Int ): Response<CatFactsResponse> {
         return  RetrofitCatInstance.api.getCatsFacts(max_length, limit)
    }
}