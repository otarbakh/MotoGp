package com.otarbakh.motogp.ui.favourite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.motogp.R
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.databinding.FragmentFavoritesBinding
import com.otarbakh.motogp.ui.adapters.TeamsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(
    FragmentFavoritesBinding::inflate
) {

    private val favsAdapter: TeamsAdapter by lazy { TeamsAdapter() }
    private val viewModel: FavoritesViewModel by viewModels()
    override fun viewCreated() {
        getTeams()
    }

    override fun listeners() {

    }

    private fun getTeams(){
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTeam().collectLatest {
                    favsAdapter.submitList(it)
                }
            }
        }

    }

    private fun setupRecycler() {
        binding.rvFavTeams.apply {
            adapter = favsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false)
        }
    }


}