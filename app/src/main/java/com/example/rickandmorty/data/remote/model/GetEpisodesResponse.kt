package com.example.rickandmorty.data.remote.model

import com.google.gson.annotations.SerializedName

data class GetEpisodesResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Episode>
)
