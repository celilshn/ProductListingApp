package com.cengcelil.productlistingapp

import com.google.gson.annotations.SerializedName


data class HorizontalProducts(

    @SerializedName("code") var code: Int? = null,
    @SerializedName("imageUrl") var imageUrl: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("dropRatio") var dropRatio: Double? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("countOfPrices") var countOfPrices: Int? = null,
    @SerializedName("followCount") var followCount: Int? = null

)