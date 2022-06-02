package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.remote.model.GetCharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): Response<GetCharactersResponse>
}
