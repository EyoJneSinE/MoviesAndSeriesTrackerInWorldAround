package com.eniskaner.moviesseriestrackerinwolrdaround.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.ActivityMainBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.masNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        setupWithNavController(bottomNavigationView, navController)

        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.masNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val fragmentTitles = mapOf(
            R.id.moviesFragment to "Movies",
            R.id.seriesFragment to "Series",
            R.id.trendingFragment to "Trending"
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.toolbar.title = fragmentTitles[navController.currentDestination?.id]

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = fragmentTitles[destination.id]
            binding.toolbar.navigationIcon = null
        }
    }

    fun setFullscreenFlags() {
        window.addFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )
    }

    fun changeFullScreenFlags(isFullSize: Boolean) {
        if (isFullSize) window.addFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        ) else window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }
}
