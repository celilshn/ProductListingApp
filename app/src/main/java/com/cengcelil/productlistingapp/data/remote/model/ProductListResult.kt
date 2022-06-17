package com.cengcelil.productlistingapp


data class ProductListResult(
    var nextUrl: String? = null,
    var horizontalProducts: ArrayList<ProductListItem> = arrayListOf(),
    var products: ArrayList<ProductListItem> = arrayListOf()

)