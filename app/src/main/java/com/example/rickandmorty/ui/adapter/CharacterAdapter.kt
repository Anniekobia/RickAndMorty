package com.example.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rickandmorty.R
import com.example.rickandmorty.data.remote.model.Character
import com.example.rickandmorty.databinding.CharacterItemRowBinding

class CharacterAdapter(
    private val characterClicked: (Character) -> Unit
) : ListAdapter<Character, CharacterAdapter.ViewHolder>(CharacterDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = CharacterItemRowBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(viewBinding)
    }

    inner class ViewHolder(private val viewBinding: CharacterItemRowBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bindItems(character: Character) {
            viewBinding.characterImage.load(character.image) {
                crossfade(true)
                placeholder(R.drawable.placeholder_image)
                transformations(CircleCropTransformation())
            }
            viewBinding.characterName.text = character.name
            viewBinding.characterSpecies.text = character.species
            viewBinding.characterGender.text = character.gender
            viewBinding.characterView.setOnClickListener {
                characterClicked(character)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    companion object CharacterDiffer : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(
            oldItem: Character,
            newItem: Character
        ): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Character,
            newItem: Character
        ): Boolean = oldItem == newItem
    }
}
