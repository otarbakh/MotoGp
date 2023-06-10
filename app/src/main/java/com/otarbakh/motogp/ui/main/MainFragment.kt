package com.otarbakh.motogp.ui.main


import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.otarbakh.motogp.R
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.databinding.FragmentMainBinding
import com.otarbakh.motogp.ui.adapters.BottomNavViewPagerAdapter

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun viewCreated() {
        setupTabLayout()
    }

    override fun listeners() {
    }

    private fun setupTabLayout() {
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        viewPager.isUserInputEnabled = false
        viewPager.adapter = BottomNavViewPagerAdapter(requireActivity())

        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> "Riders"
                1 -> "Teams"
                2 -> "Stages"
                else -> "Tab Not Found"
            }
        }.attach()
        setupTabIcons()
    }

    private fun setupTabIcons() {
        tabLayout.getTabAt(0)?.setIcon(R.drawable.racing_helmet_svgrepo_com)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_baseline_outlined_flag_24)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_baseline_more_horiz_24)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_baseline_more_horiz_24)

    }
}







