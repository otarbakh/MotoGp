package com.otarbakh.motogp.ui.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.otarbakh.motogp.ui.stages.StagesFragment2
import com.otarbakh.motogp.ui.riders.RiderFragment

import com.otarbakh.motogp.ui.teams.TeamsFragment

class BottomNavViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {RiderFragment()}
            1 -> {TeamsFragment()}
            2 -> {
                StagesFragment2()
            }



            else ->{throw Resources.NotFoundException("not found")}
        }
    }


}
