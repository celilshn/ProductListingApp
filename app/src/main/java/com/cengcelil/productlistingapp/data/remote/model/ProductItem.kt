package com.cengcelil.productlistingapp.data.remote.model

import com.google.gson.annotations.SerializedName


data class ProductItem(

    @SerializedName("mkName") var mkName: String,
    @SerializedName("productName") var productName: String,
    @SerializedName("badge") var badge: String,
    @SerializedName("rating") var rating: Float,
    @SerializedName("imageUrl") var imageUrl: String,
    @SerializedName("storageOptions") var storageOptions: ArrayList<String> = arrayListOf(),
    @SerializedName("countOfPrices") var countOfPrices: Int,
    @SerializedName("price") var price: Double,
    @SerializedName("freeShipping") var freeShipping: Boolean,
    @SerializedName("lastUpdate") var lastUpdate: String

)