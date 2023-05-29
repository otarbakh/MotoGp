package com.otarbakh.motogp.ui.stages


import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.databinding.FragmentStagesBinding
import com.otarbakh.motogp.ui.adapters.StagesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StagesFragment : BaseFragment<FragmentStagesBinding>(FragmentStagesBinding::inflate) {

    private val stagesVM: StagesViewModel by viewModels()
    private val stagesAdapter: StagesAdapter by lazy { StagesAdapter() }

    override fun viewCreated() {
        observe()
    }

    override fun listeners() {

    }

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
                            stagesAdapter.submitList(it.data.stages)
                        }
                    }
                }
            }
        }
    }


}