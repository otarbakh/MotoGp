package com.otarbakh.motogp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


import com.otarbakh.motogp.databinding.SingleTeamLayoutBinding
import com.otarbakh.motogp.domain.model.TeamDomain


class TeamsAdapter :
    ListAdapter<TeamDomain, TeamsAdapter.TeamsViewHolder>(
        TeamsDiffCallback()
    ) {

    private lateinit var itemClickListener: (TeamDomain, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): TeamsViewHolder{
        val binding =
            SingleTeamLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bindData()
    }


    inner class TeamsViewHolder(private val binding: SingleTeamLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: TeamDomain? = null
        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                tvTeamName.text = model?.name
                tvTeamNationality.text = model?.nationality



            }

            binding.tvTeamName.setOnClickListener {
                itemClickListener.invoke(model!!, adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(clickListener: (TeamDomain, Int) -> Unit) {
        itemClickListener = clickListener
    }
}


class TeamsDiffCallback : DiffUtil.ItemCallback<TeamDomain>() {
    override fun areItemsTheSame(
        oldItem: TeamDomain,
        newItem: TeamDomain,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TeamDomain,
        newItem: TeamDomain,
    ): Boolean {
        return oldItem == newItem
    }
}