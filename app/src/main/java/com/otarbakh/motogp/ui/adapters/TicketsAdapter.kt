import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otarbakh.motogp.data.model.Tickets
import com.otarbakh.motogp.databinding.SingleTicketItemLayoutBinding

class TicketsAdapter :
    ListAdapter<Tickets, TicketsAdapter.TicketsViewHolder>(
        TicketsDiffCallBack()
    ) {

    private lateinit var itemClickListener: (Tickets, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TicketsViewHolder {
        val binding =
            SingleTicketItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        holder.bindData()
    }

    inner class TicketsViewHolder(private val binding: SingleTicketItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: Tickets? = null

        fun bindData() {
            model = getItem(bindingAdapterPosition)
            binding.apply {
                binding.tvTicketType.text = model?.ticketType
                binding.tvPrice.text = model?.price
                if (model?.isAvailable == true){
                    binding.tvSoldOut.visibility = View.GONE
                    binding.tvPurchaseButton.visibility = View.VISIBLE
                }else{
                    binding.tvPurchaseButton.visibility = View.GONE
                    binding.tvSoldOut.visibility = View.VISIBLE
                }
            }
            binding.tvPurchaseButton.setOnClickListener {
                itemClickListener.invoke(model!!, absoluteAdapterPosition)
            }
        }
    }

    fun setOnItemClickListener(clickListener: (Tickets, Int) -> Unit) {
        itemClickListener = clickListener
    }
}

class TicketsDiffCallBack :
    DiffUtil.ItemCallback<Tickets>() {
    override fun areItemsTheSame(
        oldItem: Tickets,
        newItem: Tickets
    ): Boolean {
        return oldItem.ticketID == newItem.ticketID
    }

    override fun areContentsTheSame(
        oldItem: Tickets,
        newItem: Tickets
    ): Boolean {
        return oldItem == newItem
    }


}