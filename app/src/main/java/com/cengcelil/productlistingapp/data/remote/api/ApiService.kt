package com.cengcelil.productlistingapp.data.remote.api

import com.cengcelil.productlistingapp.ProductItemResponse
import com.cengcelil.productlistingapp.ProductListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET()
    suspend fun getProducts(@Url url:String):Response<ProductListResponse>

    @GET()
    suspend fun getProductDetail(@Url code:String):Response<ProductItemResponse>

}