package com.cengcelil.productlistingapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cengcelil.productlistingapp.ProductListItem
import com.cengcelil.productlistingapp.common.Util.BLANK
import com.cengcelil.productlistingapp.common.Util.format
import com.cengcelil.productlistingapp.databinding.ItemProductListHorizontalBinding

class HorizontalProductListAdapter(val clickCallback: (code: Int) -> Unit) :
    ListAdapter<ProductListItem, HorizontalProductListAdapter.HorizontalViewHolder>(
        Callback()
    ) {

    class HorizontalViewHolder(val b: ItemProductListHorizontalBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind(item: ProductListItem, clickCallback: (int: Int) -> Unit) {
            with(b) {
                with(item) {
                    val context = itemView.context

                    txtDropRatio.text = "% $dropRatio"
                    txtProductName.text = name
                    txtFollowCount.text =
                        "$followCount+ ${context.getString(com.cengcelil.productlistingapp.R.string.follow)}"
                    txtSellerCount.text =
                        countOfPrices?.let { "$it ${context.getString(com.cengcelil.productlistingapp.R.string.seller)}" }
                            ?: kotlin.run { BLANK }

                    txtProductPrice.text =
                        format.format(price)
                            ?: BLANK
                    com.bumptech.glide.Glide.with(itemView).load(imageUrl).into(imgProduct)
                    root.setOnClickListener { clickCallback.invoke(code) }
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HorizontalViewHolder(
        ItemProductListHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        holder.bind(getItem(position), clickCallback)
    }

    class Callback : DiffUtil.ItemCallback<ProductListItem>() {
        override fun areItemsTheSame(oldItem: ProductListItem, newItem: ProductListItem) =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ProductListItem,
            newItem: ProductListItem
        ) = oldItem.code == newItem.code
    }
}
