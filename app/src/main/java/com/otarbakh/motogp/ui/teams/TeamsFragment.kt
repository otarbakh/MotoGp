package com.otarbakh.motogp.ui.teams


import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.common.Resource
import com.otarbakh.motogp.databinding.FragmentTeamsBinding
import com.otarbakh.motogp.ui.adapters.StagesAdapter
import com.otarbakh.motogp.ui.adapters.TeamsAdapter
import com.otarbakh.motogp.ui.stages.StagesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeamsFragment : BaseFragment<FragmentTeamsBinding>(FragmentTeamsBinding::inflate) {

    private val teamsVM: TeamsViewModel by viewModels()
    private val teamsAdapter: TeamsAdapter by lazy { TeamsAdapter() }
    override fun viewCreated() {
        observe()
    }

    override fun listeners() {

    }
    private fun setupRecycler() {
        binding.rvTeams.apply {
            adapter = teamsAdapter
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
        teamsVM.getTeams()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                teamsVM.state.collectLatest {
                    when (it) {
                        is Resource.Error -> {

                        }

                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            teamsAdapter.submitList(it.data.teams)
                        }
                    }
                }
            }
        }
    }

}