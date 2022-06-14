package com.cengcelil.productlistingapp.data.remote.api

import com.cengcelil.productlistingapp.ProductItemResponse
import com.cengcelil.productlistingapp.ProductListResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getProducts(url: String): Response<ProductListResponse>
    suspend fun getProductDetail(code: String): Response<ProductItemResponse>
}
