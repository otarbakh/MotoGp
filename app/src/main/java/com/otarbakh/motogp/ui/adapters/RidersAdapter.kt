package com.otarbakh.motogp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otarbakh.motogp.data.summary.StageX
import com.otarbakh.motogp.databinding.SingleRiderLayoutBinding

class RidersAdapter :
    ListAdapter<StageX, RidersAdapter.RidersViewHolder>(
        LinksDiffCallback()
    ) {

    private lateinit var itemClickListener: (StageX, Int) -> Unit

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
        private var model: StageX? = null
        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                tvRiderName.text = model?.


            }

            binding.tvRiderName.setOnClickListener {
                itemClickListener.invoke(model!!, adapterPosition)
            }

        }

    }

    fun setOnItemClickListener(clickListener: (StageX, Int) -> Unit) {
        itemClickListener = clickListener
    }
}


class LinksDiffCallback : DiffUtil.ItemCallback<StageX>() {
    override fun areItemsTheSame(
        oldItem: StageX,
        newItem: StageX,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: StageX,
        newItem: StageX,
    ): Boolean {
        return oldItem == newItem
    }
}