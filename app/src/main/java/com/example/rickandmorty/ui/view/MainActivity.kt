package com.example.rickandmorty.ui.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.window.layout.WindowMetricsCalculator
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.util.NetworkMonitor

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    object Variables {
        var isNetworkConnected = MutableLiveData(false)
    }
    enum class WindowSizeClass { COMPACT, MEDIUM, EXPANDED }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()
        computeWindowSizeClasses()

        NetworkMonitor(this).registerNetworkCallbackEvents()
    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.charactersFragment,
                R.id.locationsFragment,
                R.id.episodesFragment
            )
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(binding.toolbar)
        binding.bottomNavView.setupWithNavController(navController)
        binding.navigationRail.setupWithNavController(navController)
    }

    private fun computeWindowSizeClasses() {
        val metrics = WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)
        val widthDp = metrics.bounds.width() /
            resources.displayMetrics.density
        val widthWindowSizeClass = when {
            widthDp < 600f -> WindowSizeClass.COMPACT
            widthDp < 840f -> WindowSizeClass.MEDIUM
            else -> WindowSizeClass.EXPANDED
        }

        when (widthWindowSizeClass) {
            WindowSizeClass.COMPACT -> {
                binding.navigationRail.visibility = GONE
                binding.bottomNavView.visibility = VISIBLE
            }
            WindowSizeClass.MEDIUM -> {
                binding.bottomNavView.visibility = GONE
                binding.navigationRail.visibility = VISIBLE
            }
            WindowSizeClass.EXPANDED -> {
                binding.bottomNavView.visibility = GONE
                binding.navigationRail.visibility = VISIBLE
            }
        }
    }
}
