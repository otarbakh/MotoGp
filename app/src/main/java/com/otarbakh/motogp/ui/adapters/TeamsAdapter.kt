package com.otarbakh.motogp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


import com.otarbakh.motogp.data.summary.TeamX

import com.otarbakh.motogp.databinding.SingleTeamLayoutBinding


class TeamsAdapter :
    ListAdapter<TeamX, TeamsAdapter.TeamsViewHolder>(
        TeamsDiffCallback()
    ) {

    private lateinit var itemClickListener: (TeamX, Int) -> Unit

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
        private var model: TeamX? = null
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

    fun setOnItemClickListener(clickListener: (TeamX, Int) -> Unit) {
        itemClickListener = clickListener
    }
}


class TeamsDiffCallback : DiffUtil.ItemCallback<TeamX>() {
    override fun areItemsTheSame(
        oldItem: TeamX,
        newItem: TeamX,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TeamX,
        newItem: TeamX,
    ): Boolean {
        return oldItem == newItem
    }
}