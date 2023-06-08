package com.otarbakh.motogp.ui.main


import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.otarbakh.motogp.R
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.databinding.FragmentMainBinding
import com.otarbakh.motogp.ui.riders.RiderFragment
import com.otarbakh.motogp.ui.stages.StagesFragment
import com.otarbakh.motogp.ui.teams.TeamsFragment

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private lateinit var navController: NavController
    override fun viewCreated() {
        navController = Navigation.findNavController(binding.root)
        replaceFragment(StagesFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {


            when (it.itemId) {

                R.id.riders -> replaceFragment(RiderFragment())
                R.id.stages -> replaceFragment(StagesFragment())
                R.id.teams -> replaceFragment(TeamsFragment())
                R.id.stream ->replaceFragment(fragment = Fragment())

                else -> {

                }
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()

    }


    override fun listeners() {

    }
}



