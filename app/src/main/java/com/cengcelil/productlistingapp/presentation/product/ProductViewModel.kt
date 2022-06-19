package com.cengcelil.basemvvmapp

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cengcelil.productlistingapp.ProductListResult
import com.cengcelil.productlistingapp.common.Resource
import com.cengcelil.productlistingapp.common.Status
import com.cengcelil.productlistingapp.common.Util.startUrl
import com.cengcelil.productlistingapp.data.remote.model.ProductItem
import com.cengcelil.productlistingapp.data.remote.model.ProductListPagingSource
import com.cengcelil.productlistingapp.data.remote.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    var productRepository: ProductRepository
) : ViewModel() {


    private var productDetailState = MutableLiveData<Resource<ProductItem>>()
    private var lastProductListState =
        MutableLiveData<Resource<ProductListResult>>()

    fun getProductListState() = lastProductListState
    fun getProductDetailState() = productDetailState
    val listData = Pager(PagingConfig(pageSize = 1)) {
        ProductListPagingSource(productRepository)
    }.flow.cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            getProductList()
        }
    }

    private suspend fun getProductList() = CoroutineScope(IO).launch {
        lastProductListState.postValue(Resource.loading(""))

        productRepository.getProducts(startUrl).body()?.let {
            it.productsResult.let {
                lastProductListState.postValue(Resource.success(it))
            }
        }
    }

    suspend fun getProductDetail(code: Int) = CoroutineScope(IO).launch {
        productRepository.getProductDetail(code).body()?.let {
            productDetailState.postValue(Resource.success(it.productItem))
        }

    }


}