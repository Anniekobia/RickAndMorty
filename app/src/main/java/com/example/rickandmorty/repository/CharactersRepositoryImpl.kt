package com.example.rickandmorty.repository

import androidx.lifecycle.LiveData
import com.example.rickandmorty.data.local.CharacterDAO
import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.data.remote.model.Character
import com.example.rickandmorty.data.remote.model.GetCharactersResponse
import com.example.rickandmorty.util.ErrorMessages.GENERAL_API_ERROR_MESSAGE
import com.example.rickandmorty.util.NetworkResult
import timber.log.Timber
import java.net.UnknownHostException

class CharactersRepositoryImpl(private val apiService: ApiService, private val characterDAO: CharacterDAO) : CharactersRepository {

    override suspend fun getCharacters(): NetworkResult<GetCharactersResponse> = try {
        val response = apiService.getCharacters()
        when {
            response.isSuccessful -> {
                saveAllCharacters(response.body()!!.characters)
                NetworkResult.Success(response.body()!!)
            }
            else -> NetworkResult.APIError(GENERAL_API_ERROR_MESSAGE)
        }
    } catch (e: UnknownHostException) {
        NetworkResult.NoInternetError
    } catch (e: Exception) {
        NetworkResult.ServerError
    }

    private suspend fun saveAllCharacters(characters: List<Character>) {
        val response = characterDAO.saveAllCharacters(characters)
        return when {
            response.isEmpty() -> {
                saveAllCharacters(characters)
            }
            else -> {
                Timber.e("All Characters saved")
            }
        }
    }

    override fun getSavedCharacters(): LiveData<List<Character>> = characterDAO.getAllCharacters()
}
