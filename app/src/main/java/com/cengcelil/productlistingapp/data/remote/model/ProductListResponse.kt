package com.cengcelil.productlistingapp

import com.google.gson.annotations.SerializedName


data class ProductListResponse(

    @SerializedName("result") var productsResult: ProductListResult? = ProductListResult()

)