package com.example.task04network.api.dogApi

import com.example.task04network.model.dogImages.DogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogImagesAPIService {

    @GET("{breed}/images/random/{limit}")
    suspend fun getDogsByBreeds(
        @Path("breed") breed: String,
        @Path("limit") limit: String
    ): Response<DogResponse>
}