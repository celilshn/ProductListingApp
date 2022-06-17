package com.cengcelil.productlistingapp.data.remote.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cengcelil.productlistingapp.ProductListItem
import com.cengcelil.productlistingapp.common.Util.startUrl
import com.cengcelil.productlistingapp.data.remote.repository.ProductRepository
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ProductListPagingSource @Inject constructor(
    val productRepository: ProductRepository
) : PagingSource<String, ProductListItem>() {
    override fun getRefreshKey(state: PagingState<String, ProductListItem>) = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ProductListItem> {
            productRepository.getProducts(params.key?:startUrl).body()?.productsResult?.let {
                return try {
                    LoadResult.Page(
                        it.products,
                        prevKey = null,
                        nextKey = it.nextUrl
                    )
                } catch (exception: IOException) {
                    return LoadResult.Error(exception)
                } catch (exception: HttpException) {
                    return LoadResult.Error(exception)
                }
            }
        return LoadResult.Error(Throwable("Error"))

    }

}