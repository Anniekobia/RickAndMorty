package com.example.rickandmorty.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.AbstractListDetailFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.FragmentCharacterListBinding
import com.example.rickandmorty.ui.adapter.CharacterAdapter
import com.example.rickandmorty.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CharacterListFragment : AbstractListDetailFragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private val characterViewModel: CharactersViewModel by sharedViewModel()
    private var charactersFetched = false
    private var networkConnected = false
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateListPaneView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onListPaneViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onListPaneViewCreated(view, savedInstanceState)
        val recyclerView = view as RecyclerView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
