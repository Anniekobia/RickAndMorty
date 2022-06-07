package com.example.rickandmorty.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.slidingpanelayout.widget.SlidingPaneLayout.LOCK_MODE_LOCKED
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.ui.adapter.CharacterAdapter
import com.example.rickandmorty.ui.view.MainActivity.Variables.isNetworkConnected
import com.example.rickandmorty.util.CustomOnBackPressed
import com.example.rickandmorty.util.ErrorMessages.NO_INTERNET_ERROR_MESSAGE
import com.example.rickandmorty.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private val characterViewModel: CharactersViewModel by sharedViewModel()
    private var charactersFetched = false
    private var networkConnected = false
    private var firstRun = true
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        sortCustomBackNavigation()
        setRecyclerviewAdapter()
        initObservers()
        initListeners()
        getAllCharacters()

        return binding.root
    }

    private fun setRecyclerviewAdapter() {
        characterAdapter =
            CharacterAdapter() {
                characterViewModel.updateSelectedCharacter(it)
                binding.slidingPaneLayout.openPane()
            }
        binding.recyclerView.adapter = characterAdapter
    }

    private fun initObservers() {
        isNetworkConnected.observe(viewLifecycleOwner) {
            it?.let {
                networkConnected = it
                if (!charactersFetched) {
                    showOrHideErrorView(null, false)
                    getAllCharacters()
                }
            }
        }
        characterViewModel.charactersList.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = GONE
            if (it.isNullOrEmpty()) {
                getAllCharacters()
            } else {
                characterAdapter.submitList(it)
                charactersFetched = true

                characterViewModel.updateSelectedCharacter(it.first())
                if (firstRun && binding.slidingPaneLayout.isOpen) {
                    binding.slidingPaneLayout.closePane()
                    firstRun = false
                }
            }
        }
        characterViewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = GONE
            showOrHideErrorView(it, true)
        }
        characterViewModel.noInternet.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = GONE
            showOrHideErrorView(NO_INTERNET_ERROR_MESSAGE, true)
        }
    }

    private fun initListeners() {
        binding.refresh.setOnClickListener {
            binding.errorView.visibility = GONE
            binding.progressBar.visibility = VISIBLE
            getAllCharacters()
        }
    }

    private fun getAllCharacters() {
        if (!charactersFetched) {
            binding.progressBar.visibility = VISIBLE
        }
        if (networkConnected) {
            characterViewModel.getCharacters()
        } else {
            showOrHideErrorView(NO_INTERNET_ERROR_MESSAGE, true)
        }
    }

    private fun showOrHideErrorView(message: String?, show: Boolean) {
        if (!charactersFetched && show) {
            binding.progressBar.visibility = GONE
            binding.message.text = message
            binding.errorView.visibility = VISIBLE
        } else {
            binding.errorView.visibility = GONE
        }
    }

    private fun sortCustomBackNavigation() {
        /**
         * Connect the SlidingPaneLayout to the system back button
         * This callback is only active during the fragment's life cycle.
         */
        val slidingPaneLayout = binding.slidingPaneLayout
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            CustomOnBackPressed(slidingPaneLayout)
        )
        binding.slidingPaneLayout.lockMode = LOCK_MODE_LOCKED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        firstRun = true
        _binding = null
    }
}
