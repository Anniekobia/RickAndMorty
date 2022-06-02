package com.example.rickandmorty.data.remote.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Character(
    @SerializedName("created")
    val created: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo
    val id: Int,

    @SerializedName("image")
    @ColumnInfo
    val image: String,

    @SerializedName("location")
    @Embedded(prefix = "location")
    val location: Location,

    @SerializedName("name")
    @ColumnInfo
    val name: String,

    @SerializedName("origin")
    @Embedded(prefix = "origin")
    val origin: Origin,

    @SerializedName("species")
    @ColumnInfo
    val species: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("url")
    val url: String
) : Serializable
