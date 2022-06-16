package com.cengcelil.productlistingapp

import com.cengcelil.productlistingapp.data.remote.model.ProductItem
import com.google.gson.annotations.SerializedName


data class ProductItemResponse(

    @SerializedName("result") var productItem: ProductItem

)