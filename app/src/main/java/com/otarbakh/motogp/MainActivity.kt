package com.otarbakh.motogp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.otarbakh.motogp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this,R.id.activity_main_nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView,navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.ticketsFragment ||
                destination.id == R.id.boughtTicketFragment ||
                destination.id == R.id.favoritesFragment ||
                destination.id == R.id.singleStageSummary ||
                destination.id == R.id.loginFragment ||
                destination.id == R.id.registerFragment
            ) {
                binding.bottomNavigationView.visibility = View.GONE


            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}