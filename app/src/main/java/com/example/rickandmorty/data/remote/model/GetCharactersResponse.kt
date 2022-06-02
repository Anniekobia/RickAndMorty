package com.example.rickandmorty.data.remote.model

import com.google.gson.annotations.SerializedName

data class GetCharactersResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>
)
