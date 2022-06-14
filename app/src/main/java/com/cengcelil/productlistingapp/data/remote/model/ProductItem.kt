package com.cengcelil.productlistingapp.data.remote.model

import com.google.gson.annotations.SerializedName


data class ProductItem(

    @SerializedName("mkName") var mkName: String? = null,
    @SerializedName("productName") var productName: String? = null,
    @SerializedName("badge") var badge: String? = null,
    @SerializedName("rating") var rating: Double? = null,
    @SerializedName("imageUrl") var imageUrl: String? = null,
    @SerializedName("storageOptions") var storageOptions: ArrayList<String> = arrayListOf(),
    @SerializedName("countOfPrices") var countOfPrices: Int? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("freeShipping") var freeShipping: Boolean? = null,
    @SerializedName("lastUpdate") var lastUpdate: String? = null

)