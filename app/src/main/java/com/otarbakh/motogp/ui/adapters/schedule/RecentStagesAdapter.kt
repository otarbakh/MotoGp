package com.otarbakh.motogp.ui.adapters.schedule

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otarbakh.motogp.data.model.summary.StageX

import com.otarbakh.motogp.databinding.RecentSingleStageLayoutBinding



class RecentStagesAdapter :
    ListAdapter<StageX, RecentStagesAdapter.RecentStagesViewHolder>(
        RecentStagesDiffCallback()
    ) {

    private lateinit var itemClickListener: (StageX, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): RecentStagesViewHolder {
        val binding =
            RecentSingleStageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentStagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentStagesViewHolder, position: Int) {
        holder.bindData()
    }


    inner class RecentStagesViewHolder(private val binding: RecentSingleStageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: StageX? = null
        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvGrandPrixName.text = model?.description
                tvDate.text = model?.scheduled?.drop(5)?.dropLast(15)
                Log.d("OtarBakhtadzeee", "${model?.scheduled?.drop(5)?.dropLast(15).toString()}")
                tvCountry.text = model?.venue!!.country



            }

            binding.tvCountry.setOnClickListener {
                itemClickListener.invoke(model!!, bindingAdapterPosition)
            }
        }
    }

    fun setOnItemClickListener(clickListener: (StageX, Int) -> Unit) {
        itemClickListener = clickListener
    }
}


class RecentStagesDiffCallback : DiffUtil.ItemCallback<StageX>() {
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