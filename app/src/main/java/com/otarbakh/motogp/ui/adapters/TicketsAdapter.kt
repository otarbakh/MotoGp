//package com.otarbakh.motogp.ui.adapters
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.otarbakh.motogp.data.model.TicketsEntity
//
//
//class TicketsEntityAdapter :
//    ListAdapter<TicketsEntity, TicketsEntityAdapter.TicketsEntityViewHolder>(
//        TicketsEntityDiffCallBack()
//    ) {
//
//    private lateinit var itemClickListener: (TicketsEntity, Int) -> Unit
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup, viewType: Int
//    ): TicketsEntityViewHolder {
//        val binding =
//            SingleTicketItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return TicketsEntityViewHolder(binding)
//    }
//
//
//    override fun onBindViewHolder(holder: TicketsEntityViewHolder, position: Int) {
//        holder.bindData()
//    }
//
//    inner class TicketsEntityViewHolder(private val binding: SingleTicketItemLayoutBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        private var model: TicketsEntity? = null
//
//        fun bindData() {
//            model = getItem(bindingAdapterPosition)
//            binding.apply {
//                binding.tvTicketType.text = model?.ticketType
//                binding.tvPrice.text = model?.price
//                if (model?.isAvailable == true){
//                    binding.tvSoldOut.visibility = View.GONE
//                    binding.tvPurchaseButton.visibility = View.VISIBLE
//                }else{
//                    binding.tvPurchaseButton.visibility = View.GONE
//                    binding.tvSoldOut.visibility = View.VISIBLE
//                }
//            }
//            binding.tvPurchaseButton.setOnClickListener {
//                itemClickListener.invoke(model!!, absoluteAdapterPosition)
//            }
//        }
//    }
//
//    fun setOnItemClickListener(clickListener: (TicketsEntity, Int) -> Unit) {
//        itemClickListener = clickListener
//    }
//}
//
//class TicketsEntityDiffCallBack :
//    DiffUtil.ItemCallback<TicketsEntity>() {
//    override fun areItemsTheSame(
//        oldItem: TicketsEntity,
//        newItem: TicketsEntity
//    ): Boolean {
//        return oldItem.ticketID == newItem.ticketID
//    }
//
//    override fun areContentsTheSame(
//        oldItem: TicketsEntity,
//        newItem: TicketsEntity
//    ): Boolean {
//        return oldItem == newItem
//    }
//
//
//}