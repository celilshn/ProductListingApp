package com.cengcelil.productlistingapp

import com.google.gson.annotations.SerializedName


data class ProductListResult(
    var nextUrl: String? = null,
    var horizontalProducts: ArrayList<HorizontalProducts> = arrayListOf(),
    var products: ArrayList<ProductListItem> = arrayListOf()

)