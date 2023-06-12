package com.otarbakh.motogp.ui.bought_ticket

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.databinding.FragmentBoughtTicketBinding
import com.otarbakh.motogp.ui.adapters.schedule.BoughtTicketsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BoughtTicketsFragment :
    BaseFragment<FragmentBoughtTicketBinding>(FragmentBoughtTicketBinding::inflate) {
    private val ticketsAdapter: BoughtTicketsAdapter by lazy { BoughtTicketsAdapter() }
    private val ticketsViewModel: BoughtTicketViewModel by viewModels()


    override fun viewCreated() {
        setupRecycler()
        getTickets()
    }

    override fun listeners() {
        share()
        cancelTicket()
    }

    private fun share() {
        ticketsAdapter.apply {
            setOnItemClickListener { ticket, _ ->
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "This is the ticket i bought for Formula 1. it will take place in ${ticket.raceName} at ${ticket.raceDay}. Good luck petrolhead!"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }

    private fun cancelTicket() {
        ticketsAdapter.apply {
            setOnDeleteClickListener { ticketsEntity, i ->
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Do you really want to return this ticket?")
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                            ticketsViewModel.deleteTicket(ticketsEntity)
                            Toast.makeText(requireContext(), "deleted!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(requireContext(), "you kept your ticket!", Toast.LENGTH_SHORT)
                        .show()
                }
                builder.show()
            }
        }
    }

    private fun getTickets() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                ticketsViewModel.getTicket().collect {
                    ticketsAdapter.submitList(it)
                }
            }
        }
    }

    private fun setupRecycler() {
        binding.rvBoughtTickets.apply {
            adapter = ticketsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

}