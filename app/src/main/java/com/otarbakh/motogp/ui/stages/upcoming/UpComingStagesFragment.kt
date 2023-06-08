package com.otarbakh.motogp.ui.stages.upcoming

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.motogp.R
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.databinding.FragmentUpcomingStagesBinding
import com.otarbakh.motogp.ui.adapters.schedule.UpComingStagesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpComingStagesFragment: BaseFragment<FragmentUpcomingStagesBinding>(FragmentUpcomingStagesBinding::inflate) {
    private val stagesVM: UpComingStagesViewModel by viewModels()
    private val stagesAdapter: UpComingStagesAdapter by lazy { UpComingStagesAdapter() }

    override fun viewCreated() {
        observe()
        observeWeather()

    }

    override fun listeners() {
        goToTicket()
    }

    fun goToTicket(){
        stagesAdapter.setOnItemClickListener { stageX, i ->
            findNavController().navigate(R.id.action_upComingStagesFragment_to_ticketsFragment)
        }

    }
//##################################################################################################
    private fun setupRecycler() {
        binding.rvStages.apply {
            adapter = stagesAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }
    private fun observe() {
        setupRecycler()
        stagesVM.getStages()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                stagesVM.state.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }

                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            stagesAdapter.submitList(it.data)
                            val lat = it.data?.get(0)?.venue?.coordinates?.dropLast(10)?.toDouble()
                            val long = it.data?.get(0)?.venue?.coordinates?.drop(10)?.toDouble()
                            Log.d("mishiko", long.toString())

                            if (long != null && lat != null) {
                                stagesVM.getWeather(lat,long)
                            }
                            binding.apply {
                                tvLocation.text = it.data?.get(0)?.venue?.country
                                tvRaceName.text = it.data?.get(0)?.venue?.city
                            }

                        }

                        else -> {}
                    }
                }
            }
        }
    }
    private fun observeWeather() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                stagesVM.weatherState.collect() {
                    when(it){
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            binding.tvWeather.text = "${it.data.temperature2mMax[0]} C\u00B0"
                            weatherIcon(it.data.weatherCode[0])
                        }
                    }
                }
            }
        }
    }
    private fun weatherIcon(data:Int){
        if ( data in 0..3 ){
            binding.ivWeatherIcon.setImageResource(R.drawable.sun)
        }
        else if(data in 51..67){
            binding.ivWeatherIcon.setImageResource(R.drawable.rain)
        }
        else if(data in 95..99){
            binding.ivWeatherIcon.setImageResource(R.drawable.thunder)
        }
        else{
            binding.ivWeatherIcon.setImageResource(R.drawable.clouds)
        }
    }
}