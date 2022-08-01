package com.example.task04network.model.dogImages

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("message") val dogImage: List<String>
)
