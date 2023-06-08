package com.otarbakh.motogp.ui.stages


import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.databinding.FragmentStagesBinding
import com.otarbakh.motogp.ui.adapters.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StagesFragment : BaseFragment<FragmentStagesBinding>(FragmentStagesBinding::inflate) {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun viewCreated() {
        setupTabLayout()
    }

    override fun listeners() {

    }


    private fun setupTabLayout() {
        viewPager = binding.viewPager2
        tabLayout = binding.tabLayout
        viewPager.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(tabLayout,viewPager){tab,index ->
            tab.text = when(index){
                0 -> {"Recent Races"}
                1 -> {"Upcoming Races"}
                else -> {"Tab Not Found"}
            }
        }.attach()
    }
}