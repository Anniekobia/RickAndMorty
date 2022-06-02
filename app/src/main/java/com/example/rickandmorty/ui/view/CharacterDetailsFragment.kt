package com.example.rickandmorty.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rickandmorty.R
import com.example.rickandmorty.data.remote.model.Character
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmorty.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private val characterViewModel: CharactersViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        setUpData()

        return binding.root
    }

    private fun setUpData() {
        characterViewModel.selectedCharacter.observe(viewLifecycleOwner) {
            it?.let {
                bindDetails(it)
            }
        }
        val character = arguments?.getSerializable("Character")
        if (character != null) {
            bindDetails(character as Character)
        }
    }

    private fun bindDetails(character: Character) {
        binding.characterImage.load(character.image) {
            crossfade(true)
            placeholder(R.drawable.placeholder_image)
            transformations(CircleCropTransformation())
        }
        binding.characterName.text = character.name
        binding.characterActorName.text = character.species
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
