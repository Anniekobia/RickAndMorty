package com.example.rickandmorty.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.remote.model.Character

@Dao
interface CharacterDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCharacters(characterList: List<Character>): List<Long>

    @Query("SELECT * FROM character")
    fun getAllCharacters(): LiveData<List<Character>>
}
