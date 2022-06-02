package com.example.rickandmorty.repository

import androidx.lifecycle.LiveData
import com.example.rickandmorty.data.remote.model.Character
import com.example.rickandmorty.data.remote.model.GetCharactersResponse
import com.example.rickandmorty.util.NetworkResult

interface CharactersRepository {
    suspend fun getCharacters(): NetworkResult<GetCharactersResponse>
    fun getSavedCharacters(): LiveData<List<Character>>
}
