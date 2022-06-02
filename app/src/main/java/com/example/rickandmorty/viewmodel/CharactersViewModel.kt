package com.example.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.remote.model.Character
import com.example.rickandmorty.repository.CharactersRepository
import com.example.rickandmorty.util.ErrorMessages.SERVICE_UNAVAILABLE_ERROR_MESSAGE
import com.example.rickandmorty.util.NetworkResult
import com.example.rickandmorty.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel(private val characterRepository: CharactersRepository) : ViewModel() {

    val charactersList: LiveData<List<Character>> by lazy {
        characterRepository.getSavedCharacters()
    }

    // ToDo SlidingPaneLayout
    private val _selectedCharacter = SingleLiveEvent<Character>()
    val selectedCharacter: LiveData<Character> get() = _selectedCharacter

    private val _errorMessage = SingleLiveEvent<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _noInternet = SingleLiveEvent<Boolean>()
    val noInternet: LiveData<Boolean> get() = _noInternet

    fun getCharacters() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = characterRepository.getCharacters()) {
            is NetworkResult.Success -> { }
            is NetworkResult.APIError -> _errorMessage.postValue(result.errorMessage)
            is NetworkResult.NoInternetError -> _noInternet.postValue(true)
            is NetworkResult.ServerError -> _errorMessage.postValue(
                SERVICE_UNAVAILABLE_ERROR_MESSAGE
            )
        }
    }

    fun updateSelectedCharacter(character: Character) {
        _selectedCharacter.value = character
    }
}
