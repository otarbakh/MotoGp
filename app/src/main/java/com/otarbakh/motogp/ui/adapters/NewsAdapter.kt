package com.otarbakh.motogp.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.otarbakh.motogp.databinding.SingleNewsLayoutBinding
import com.otarbakh.motogp.domain.model.ArticleDomain

class NewsAdapter :
    PagingDataAdapter<ArticleDomain, NewsAdapter.PlayersViewHolder>(
        PlayersDiffCallback()
    ) {


    private lateinit var itemGotoLinkClickListener: (ArticleDomain, Int) -> Unit
    private lateinit var itemShareClickListener: (ArticleDomain, Int) -> Unit

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {

        val data = getItem(position)

        holder.bind(data)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {

        return PlayersViewHolder(
            SingleNewsLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    inner class PlayersViewHolder(
        private val binding: SingleNewsLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ArticleDomain?) {

            binding.apply {
                tvNewsText.text = data?.title
                tvDesription.text = data?.description
                tvSource.text = data?.source?.name


                binding.ivNewsImage.setOnClickListener {
                    itemGotoLinkClickListener.invoke(data!!, absoluteAdapterPosition)
                }

                binding.btnShare.setOnClickListener {
                    itemShareClickListener.invoke(data!!, absoluteAdapterPosition)
                }

                Glide.with(this.ivNewsImage)
                    .load(data?.urlToImage)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(44)))
                    .into(ivNewsImage)
            }

        }


    }

    fun setOnGotoClickListener(clickListener: (ArticleDomain, Int) -> Unit) {
        itemGotoLinkClickListener = clickListener
    }

    fun setOnShareClickListener(clickListener: (ArticleDomain, Int) -> Unit) {
        itemShareClickListener = clickListener
    }




    private class PlayersDiffCallback : DiffUtil.ItemCallback<ArticleDomain>() {
        override fun areItemsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain): Boolean {
            return oldItem == newItem
        }
    }


}