package com.example.rickandmorty.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.util.NetworkMonitor

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    object Variables {
        var isNetworkConnected = MutableLiveData(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()

        NetworkMonitor(this).registerNetworkCallbackEvents()
    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.charactersFragment,
                R.id.locationsFragment,
                R.id.episodesFragment
            )
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
//        binding.navigationBar?.setupWithNavController(navController)
        setUpNavigationRail(navController)
    }

    private fun setUpNavigationRail(navController: NavController) {
        binding.navigationRail?.setupWithNavController(navController)
        binding.navigationRail?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.charactersFragment -> {
                    navController.navigate(R.id.charactersFragment)
                    true
                }
                R.id.locationsFragment -> {
                    navController.navigate(R.id.charactersFragment)
                    true
                }
                R.id.episodesFragment -> {
                    navController.navigate(R.id.charactersFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
