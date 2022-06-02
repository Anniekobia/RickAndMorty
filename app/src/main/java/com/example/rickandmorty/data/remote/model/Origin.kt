package com.example.rickandmorty.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Origin(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
): Serializable
