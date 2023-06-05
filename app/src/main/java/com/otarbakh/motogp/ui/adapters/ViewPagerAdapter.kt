package com.otarbakh.motogp.ui.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.otarbakh.motogp.ui.stages.recent.RecentStagesFragment
import com.otarbakh.motogp.ui.stages.upcoming.UpComingStagesFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {RecentStagesFragment()}
            1 -> {UpComingStagesFragment()}

            else ->{throw Resources.NotFoundException("not found")}
        }
    }


}
