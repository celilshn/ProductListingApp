package com.cengcelil.productlistingapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cengcelil.productlistingapp.ProductListItem
import com.cengcelil.productlistingapp.R
import com.cengcelil.productlistingapp.common.Util.BLANK
import com.cengcelil.productlistingapp.common.Util.format
import com.cengcelil.productlistingapp.common.Util.numberFormat
import com.cengcelil.productlistingapp.databinding.ItemProductListBinding
import java.text.DecimalFormat

class VerticalProductListAdapter(val clickCallback: (code: Int) -> Unit) :
    PagingDataAdapter<ProductListItem, VerticalProductListAdapter.VerticalViewHolder>(
        Callback()
    ) {

    class VerticalViewHolder(val b: ItemProductListBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(item: ProductListItem?, clickCallback: (int: Int) -> Unit) {
            item?.let {
                with(b) {
                    with(item) {
                        val context = itemView.context

                        txtDropRatio.text = "% $dropRatio"
                        txtProductName.text = name
                        txtFollowCount.text =
                            "$followCount+ ${context.getString(R.string.follow)}"
                        txtSellerCount.text =
                            countOfPrices.let { "$it ${context.getString(R.string.seller)}" }

                        txtProductPrice.text =
                            numberFormat.format(price) ?: BLANK
                        com.bumptech.glide.Glide.with(itemView).load(imageUrl).into(imgProduct)
                        root.setOnClickListener { clickCallback.invoke(code) }
                    }
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VerticalViewHolder(
        ItemProductListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) =
        holder.bind(getItem(position), clickCallback)


    class Callback : DiffUtil.ItemCallback<ProductListItem>() {
        override fun areItemsTheSame(oldItem: ProductListItem, newItem: ProductListItem) =
            oldItem.code == newItem.code

        override fun areContentsTheSame(
            oldItem: ProductListItem,
            newItem: ProductListItem
        ) = oldItem.code == newItem.code
    }


}

