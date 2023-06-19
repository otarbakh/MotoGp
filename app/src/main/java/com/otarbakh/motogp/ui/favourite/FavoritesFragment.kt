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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
        delete()

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

    private fun delete() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return true
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val team = favsAdapter.currentList[position]
                viewModel.deleteTeam(team)
                favsAdapter.submitList(favsAdapter.currentList.toList())
                Snackbar.make(view!!, "Deleted Team", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.addTeam(team)
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvFavTeams)
        }
    }


}