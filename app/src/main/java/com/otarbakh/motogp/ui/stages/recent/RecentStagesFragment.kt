package com.otarbakh.motogp.ui.stages.recent

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.databinding.FragmentRecentStagesBinding
import com.otarbakh.motogp.ui.adapters.StagesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecentStagesFragment: BaseFragment<FragmentRecentStagesBinding>(FragmentRecentStagesBinding::inflate) {

    private val recentVM: RecentStagesViewModel by viewModels()
    private val recentAdapter: StagesAdapter by lazy { StagesAdapter() }

    override fun viewCreated() {
        observe()
    }

    override fun listeners() {

    }

    private fun setupRecycler() {
        binding.rvRecentStages.apply {
            adapter = recentAdapter
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
        recentVM.getStages()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                recentVM.state.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }

                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            recentAdapter.submitList(it.data)
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}