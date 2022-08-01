package com.example.task04network.repository

import com.example.task04network.model.catFacts.CatFactsResponse
import com.example.task04network.model.dogImages.DogResponse
import retrofit2.Response
import retrofit2.http.Url

interface AnimalsRepository {
    suspend fun getDogImages(breed: String, limit: String): Response<DogResponse>
    suspend fun getCatFacts(max_length: Int, limit: Int): Response<CatFactsResponse>
}