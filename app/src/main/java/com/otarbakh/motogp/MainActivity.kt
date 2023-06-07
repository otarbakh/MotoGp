package com.otarbakh.motogp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.otarbakh.motogp.databinding.ActivityMainBinding
import com.otarbakh.motogp.ui.riders.RiderFragment
import com.otarbakh.motogp.ui.stages.StagesFragment
import com.otarbakh.motogp.ui.teams.TeamsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
//        replaceFragment(RiderFragment())

//
//        val bottomNaviagtionview: BottomNavigationView = findViewById(R.id.bottomNavigationView)
//        val navController: NavController = findNavController(R.id.nav_host_fragment)
//        bottomNaviagtionview.setOnItemSelectedListener {
//            when (it.itemId) {
//
//
//
//                else -> {
//
//                }
//            }
//            true
//        }
//    }

//}
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val navController = findNavController(R.id.navHost)
//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
//    }

//}


//        val navController = binding.navHost
//        binding.bottomNavigationView.setupWithNavController(navController)

//        binding.bottomNavigationView.setOnItemSelectedListener {
//
//            when(it.itemId){
//
//                R.id.home -> replaceFragment(RiderFragment())
//                R.id.profile -> replaceFragment(StagesFragment())
//                R.id.settings -> replaceFragment(TeamsFragment())
//                R.id.stream ->replaceFragment(fragment = Fragment())
//
//                else ->{
//
//                }
//            }
//            true
//        }
//    }
//   private fun replaceFragment(fragment: Fragment){
//
//       val fragmentManager = supportFragmentManager
//       val fragmentTransaction = fragmentManager.beginTransaction()
//       fragmentTransaction.replace(R.id.frameLayout,fragment)
//       fragmentTransaction.commit()
//
//   }
//}