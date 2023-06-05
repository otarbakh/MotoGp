package com.otarbakh.motogp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otarbakh.motogp.data.model.summary.Competitor
import com.otarbakh.motogp.data.model.summary.StageX
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
            model = getItem(bindingAdapterPosition)
            binding.apply {
                tvGrandPrixName.text = model?.description
                tvDate.text = model?.scheduled?.drop(5)?.dropLast(15)
                Log.d("OtarBakhtadzeee", "${model?.scheduled?.drop(5)?.dropLast(15).toString()}")
                tvCountry.text = model?.venue!!.country
                tvTime.text = model?.scheduled?.drop(11)?.dropLast(9).toString()


            }

            binding.tvDate.setOnClickListener {
                itemClickListener.invoke(model!!, bindingAdapterPosition)
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