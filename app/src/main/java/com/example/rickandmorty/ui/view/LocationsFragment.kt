package com.example.rickandmorty.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickandmorty.databinding.FragmentLocationsBinding
import com.example.rickandmorty.ui.adapter.CharacterAdapter
import com.example.rickandmorty.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LocationsFragment : Fragment() {

    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private val characterViewModel: CharactersViewModel by sharedViewModel()
    private var charactersFetched = false
    private var networkConnected = false
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
