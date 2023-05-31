package com.otarbakh.motogp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.otarbakh.motogp.databinding.ActivityMainBinding
import com.otarbakh.motogp.ui.riders.RiderFragment
import com.otarbakh.motogp.ui.stages.StagesFragment
import com.otarbakh.motogp.ui.teams.TeamsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(RiderFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> replaceFragment(RiderFragment())
                R.id.profile -> replaceFragment(StagesFragment())
                R.id.settings -> replaceFragment(TeamsFragment())

                else ->{

                }
            }
            true
        }
    }
   private fun replaceFragment(fragment: Fragment){

       val fragmentManager = supportFragmentManager
       val fragmentTransaction = fragmentManager.beginTransaction()
       fragmentTransaction.replace(R.id.frameLayout,fragment)
       fragmentTransaction.commit()

   }
}