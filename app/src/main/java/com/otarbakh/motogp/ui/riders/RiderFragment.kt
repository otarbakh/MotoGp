package com.otarbakh.motogp.ui.riders

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.databinding.FragmentRiderBinding
import com.otarbakh.motogp.ui.adapters.RidersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RiderFragment :BaseFragment<FragmentRiderBinding>(FragmentRiderBinding::inflate) {

    private val ridersVM: RiderViewModel by viewModels()
    private val ridersAdapter: RidersAdapter by lazy { RidersAdapter() }
    override fun viewCreated() {
        setupRecycler()
        observe()

    }

    override fun listeners() {

    }


    private fun setupRecycler() {
        binding.rvRiders.apply {
            adapter = ridersAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }


    private fun observe(){
        setupRecycler()
        ridersVM.getRiders()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                ridersVM.state.collectLatest {
                    when(it){
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            ridersAdapter.submitList(it.data.competitors)
                        }
                    }
                }
            }
        }
    }

}