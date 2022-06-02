package com.example.rickandmorty.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
): Serializable
