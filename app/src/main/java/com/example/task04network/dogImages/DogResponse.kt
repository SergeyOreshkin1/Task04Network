package com.example.task04network.dogImages

import com.google.gson.annotations.SerializedName

data class DogResponse(
    var status: String,
    @SerializedName("message") var dogImage: List<String>
)
