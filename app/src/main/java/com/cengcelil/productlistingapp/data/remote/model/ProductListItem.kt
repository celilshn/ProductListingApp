package com.cengcelil.productlistingapp



data class ProductListItem(
    var code: Int,
    var imageUrl: String,
    var name: String,
    var dropRatio: Double,
    var price: Double,
    var countOfPrices: Int,
    var followCount: Int
)