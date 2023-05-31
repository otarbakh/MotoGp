package com.otarbakh.motogp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otarbakh.motogp.data.model.summary.Competitor
import com.otarbakh.motogp.databinding.SingleRiderLayoutBinding

class RidersAdapter :
    ListAdapter<Competitor, RidersAdapter.RidersViewHolder>(
        RidesDiffCallback()
    ) {

    private lateinit var itemClickListener: (Competitor, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): RidersViewHolder {
        val binding =
            SingleRiderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RidersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RidersViewHolder, position: Int) {
        holder.bindData()
    }


    inner class RidersViewHolder(private val binding: SingleRiderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: Competitor? = null
        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                tvRiderName.text = model?.name
                tvRiderNationality.text = model?.nationality


            }

            binding.tvRiderName.setOnClickListener {
                itemClickListener.invoke(model!!, adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(clickListener: (Competitor, Int) -> Unit) {
        itemClickListener = clickListener
    }
}


class RidesDiffCallback : DiffUtil.ItemCallback<Competitor>() {
    override fun areItemsTheSame(
        oldItem: Competitor,
        newItem: Competitor,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Competitor,
        newItem: Competitor,
    ): Boolean {
        return oldItem == newItem
    }
}