package com.cengcelil.productlistingapp.data.remote.repository

import com.cengcelil.productlistingapp.ProductItemResponse
import com.cengcelil.productlistingapp.ProductListResponse
import com.cengcelil.productlistingapp.data.remote.api.ApiHelper
import com.cengcelil.productlistingapp.data.remote.api.ApiService
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override suspend fun getProducts(url:String): Response<ProductListResponse> = apiService.getProducts(url)
    override suspend fun getProductDetail(code:String): Response<ProductItemResponse> = apiService.getProductDetail(code)

}