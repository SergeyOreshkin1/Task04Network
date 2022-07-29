package com.example.task04network.dogImages

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DogImagesAPIService {
    @GET
    suspend fun getDogsByBreeds(@Url url: String): Response<DogResponse>
}