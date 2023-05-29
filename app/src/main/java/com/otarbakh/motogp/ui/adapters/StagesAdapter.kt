package com.otarbakh.motogp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otarbakh.motogp.data.summary.Competitor
import com.otarbakh.motogp.data.summary.StageX
import com.otarbakh.motogp.databinding.SingleRiderLayoutBinding
import com.otarbakh.motogp.databinding.SingleStageLayoutBinding

class StagesAdapter :
    ListAdapter<StageX, StagesAdapter.StagesViewHolder>(
        StagesDiffCallback()
    ) {

    private lateinit var itemClickListener: (StageX, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): StagesViewHolder{
        val binding =
            SingleStageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StagesViewHolder, position: Int) {
        holder.bindData()
    }


    inner class StagesViewHolder(private val binding: SingleStageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: StageX? = null
        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                tvGrandPrixName.text = model?.description
                tvDate.text = model?.scheduled
                tvCountry.text = model?.venue!!.country


            }

            binding.tvDate.setOnClickListener {
                itemClickListener.invoke(model!!, adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(clickListener: (StageX, Int) -> Unit) {
        itemClickListener = clickListener
    }
}


class StagesDiffCallback : DiffUtil.ItemCallback<StageX>() {
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