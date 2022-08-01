package com.example.task04network.api.catAPI

import com.example.task04network.model.catFacts.CatFactsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactsAPIService {
    companion object {
        private const val GET_CATS_FACTS = "/facts"
    }

    @GET(GET_CATS_FACTS)
    suspend fun getCatsFacts(
        @Query("max_length") max_length: Int,
        @Query("limit") limit: Int,
    ): Response<CatFactsResponse>
}