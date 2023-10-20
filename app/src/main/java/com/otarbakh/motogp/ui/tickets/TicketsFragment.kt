package com.otarbakh.motogp.ui.tickets

import TicketsAdapter
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.otarbakh.motogp.R
import com.otarbakh.motogp.common.BaseFragment
import com.otarbakh.motogp.data.model.Tickets
import com.otarbakh.motogp.data.model.TicketsEntity
import com.otarbakh.motogp.databinding.FragmentTicketsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TicketsFragment :
    BaseFragment<FragmentTicketsBinding>(FragmentTicketsBinding::inflate) {
    private val args: TicketsFragmentArgs by navArgs()

    private val ticketsAdapter: TicketsAdapter by lazy { TicketsAdapter() }
    private val ticketsViewModel: TicketsViewModel by viewModels()

    private val ticketsList = mutableListOf<Tickets>()


    override fun viewCreated() {
        popTicketsList()
        setupRecycler()

        ticketsAdapter.submitList(ticketsList)

        binding.tvOptionsAvailable.text = "${ticketsList.size} options available"

        val ticketInfo = args.StageInfo
        binding.tvRaceDate.text = ticketInfo?.date
        binding.tvTrackName.text = ticketInfo?.trackName
    }


    override fun listeners() {
        ticketsAdapter.apply {
            setOnItemClickListener { tickets, i ->
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Are you sure you want to buy ${tickets.ticketType} ticket?")
                builder.setPositiveButton(R.string.yes_sure) { dialog, which ->
                    insertTicket()
                    Toast.makeText(requireContext(), R.string.you_bought_ticket, Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(requireContext(), R.string.canceled, Toast.LENGTH_SHORT).show()
                }
                builder.show()
            }
        }
    }

    private fun insertTicket() {
        val ticket = TicketsEntity(
            0,
            args.StageInfo!!.date,
            args.StageInfo!!.trackName
        )
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                ticketsViewModel.insertTicket(ticket)
            }
        }
    }


    private fun setupRecycler() {
        binding.rvTickets.apply {
            adapter = ticketsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    private fun popTicketsList() {
        ticketsList.add(
            Tickets(
                1,
                "Paddock Club",
                "5000 EUR",
                true
            )
        )

        ticketsList.add(
            Tickets(
                2,
                "Main Grandstand",
                "3000 EUR",
                false
            )
        )
        ticketsList.add(
            Tickets(
                3,
                "Grandstand 4",
                "1300 EUR",
                false
            )
        )

        ticketsList.add(
            Tickets(
                4,
                "Grandstand 3",
                "1100 EUR",
                false
            )
        )

        ticketsList.add(
            Tickets(
                5,
                "Grandstand 2",
                "1000 EUR",
                true
            )
        )

        ticketsList.add(
            Tickets(
                6,
                "Grandstand 1",
                "700 EUR",
                true
            )
        )


    }

}