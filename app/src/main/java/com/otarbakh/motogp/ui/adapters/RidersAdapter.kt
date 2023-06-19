package com.otarbakh.motogp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otarbakh.motogp.R
import com.otarbakh.motogp.data.model.summary.Competitor
import com.otarbakh.motogp.databinding.SingleRiderLayoutBinding
import com.otarbakh.motogp.ui.riders.Riders

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
            model = getItem(absoluteAdapterPosition)
            binding.apply {
                tvDriverName.text = model?.name
                tvTeam.text = model?.team!!.name
                tvPoints.text = model?.result!!.points.toString()

                if (model?.result!!.position == null) {
                    tvPosition.text = "n/a"
                } else {
                    tvPosition.text = model?.result!!.position.toString()
                }


                if (model?.result!!.points == null) {
                    tvPoints.text = "0"
                } else {
                    tvPoints.text = model?.result!!.points.toString()
                }




                when (model!!.name) {

                    Riders.BINDER.lastName -> binding.ivTeamIndicator.setImageResource(Riders.BINDER.imageResourceId)
                    Riders.ESPARGARO_A.lastName -> binding.ivTeamIndicator.setImageResource(Riders.ESPARGARO_A.imageResourceId)
                    Riders.MARQUEZ_A.lastName -> binding.ivTeamIndicator.setImageResource(Riders.MARQUEZ_A.imageResourceId)
                    Riders.RINS.lastName -> binding.ivTeamIndicator.setImageResource(Riders.RINS.imageResourceId)
                    Riders.FERNANDEZ_A.lastName -> binding.ivTeamIndicator.setImageResource(Riders.FERNANDEZ_A.imageResourceId)
                    Riders.BASTIANINI.lastName -> binding.ivTeamIndicator.setImageResource(Riders.BASTIANINI.imageResourceId)
                    Riders.QUARTARARO.lastName -> binding.ivTeamIndicator.setImageResource(Riders.QUARTARARO.imageResourceId)
                    Riders.GIANNATONIO.lastName -> binding.ivTeamIndicator.setImageResource(Riders.GIANNATONIO.imageResourceId)
                    Riders.BAGNAIA.lastName -> binding.ivTeamIndicator.setImageResource(Riders.BAGNAIA.imageResourceId)
                    Riders.MORDBIDELLI.lastName -> binding.ivTeamIndicator.setImageResource(Riders.MORDBIDELLI.imageResourceId)
                    Riders.MILLER.lastName -> binding.ivTeamIndicator.setImageResource(Riders.MILLER.imageResourceId)
                    Riders.MIR.lastName -> binding.ivTeamIndicator.setImageResource(Riders.MIR.imageResourceId)
                    Riders.MARINI.lastName -> binding.ivTeamIndicator.setImageResource(Riders.MARINI.imageResourceId)
                    Riders.MARQUEZ_M.lastName -> binding.ivTeamIndicator.setImageResource(Riders.MARQUEZ_M.imageResourceId)
                    Riders.BEZZECCHI.lastName -> binding.ivTeamIndicator.setImageResource(Riders.BEZZECCHI.imageResourceId)
                    Riders.VINIALES.lastName -> binding.ivTeamIndicator.setImageResource(Riders.VINIALES.imageResourceId)
                    Riders.OLIVEIRA.lastName -> binding.ivTeamIndicator.setImageResource(Riders.OLIVEIRA.imageResourceId)
                    Riders.ESPARGARO_P.lastName -> binding.ivTeamIndicator.setImageResource(Riders.ESPARGARO_P.imageResourceId)
                    Riders.FERNANDEZ_R.lastName -> binding.ivTeamIndicator.setImageResource(Riders.FERNANDEZ_R.imageResourceId)
                    Riders.NAKAGAMI.lastName -> binding.ivTeamIndicator.setImageResource(Riders.NAKAGAMI.imageResourceId)
                }


            }

            binding.tvDriverName.setOnClickListener {
                itemClickListener.invoke(model!!, absoluteAdapterPosition)
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