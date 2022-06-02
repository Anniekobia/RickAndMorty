package com.example.rickandmorty.data.remote.model

import com.google.gson.annotations.SerializedName

data class GetLocationsResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val locationDetaileds: List<LocationDetailed>
)
