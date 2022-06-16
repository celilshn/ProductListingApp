package com.cengcelil.productlistingapp.data.remote.repository

import com.cengcelil.productlistingapp.data.remote.api.ApiHelper
import javax.inject.Inject
import kotlin.text.StringBuilder

class ProductRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    companion object {
        const val productDetailBaseUrl =
            "https://mocki.io/v1/1a1fb542-22d1-4919-914a-750114879775?code="
    }

    suspend fun getProducts(url: String) = apiHelper.getProducts(url)

    suspend fun getProductDetail(code: Int) = apiHelper.getProductDetail(
        StringBuilder().append(productDetailBaseUrl).append("{$code}").toString()
    )
}