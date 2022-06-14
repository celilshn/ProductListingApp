package com.cengcelil.productlistingapp

import com.google.gson.annotations.SerializedName


data class ProductListResult(

    @SerializedName("nextUrl") var nextUrl: String? = null,
    @SerializedName("horizontalProducts") var horizontalProducts: ArrayList<HorizontalProducts> = arrayListOf(),
    @SerializedName("products") var products: ArrayList<ProductListItem> = arrayListOf()

)