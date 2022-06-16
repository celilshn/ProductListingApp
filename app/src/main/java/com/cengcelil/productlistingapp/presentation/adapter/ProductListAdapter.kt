package com.cengcelil.productlistingapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cengcelil.productlistingapp.ProductListItem
import com.cengcelil.productlistingapp.R
import com.cengcelil.productlistingapp.databinding.ItemProductListBinding
import java.text.DecimalFormat

class ProductListAdapter(val clickCallback: (code: Int?) -> Unit) :
    ListAdapter<ProductListItem, ProductListAdapter.ItemHolder>(Callback()) {
    companion object {
        private val format = DecimalFormat("##.##")
        private val BLANK = ""
    }

    inner class ItemHolder(val b: ItemProductListBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemHolder(
        ItemProductListBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: ItemHolder, position: Int) = with(holder.b) {
        with(currentList[position]) {
            val context = holder.itemView.context

            txtDropRatio.text = "% $dropRatio"
            txtProductName.text = name
            txtFollowCount.text = "$followCount+ ${context.getString(R.string.follow)}"
            txtSellerCount.text = countOfPrices?.let { "$it ${context.getString(R.string.seller)}" }
                ?: kotlin.run { BLANK }

            txtProductPrice.text = format.format(price)?: BLANK
            Glide.with(holder.itemView).load(imageUrl).into(imgProduct)
            root.setOnClickListener { clickCallback.invoke(code) }
        }

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