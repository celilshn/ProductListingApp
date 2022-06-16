package com.cengcelil.basemvvmapp

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cengcelil.productlistingapp.ProductListResponse
import com.cengcelil.productlistingapp.ProductListResult
import com.cengcelil.productlistingapp.common.Resource
import com.cengcelil.productlistingapp.data.remote.model.ProductItem
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

    private var nextUrlLiveData =
        MutableLiveData<String?>("https://mocki.io/v1/59906f35-d5d5-40f7-8d44-53fd26eb3a05")

    private var productDetailState = MutableLiveData<Resource<ProductItem>>()
    private var lastProductListState =
        MutableLiveData<Resource<ProductListResult>>()

    fun getProductListState() = lastProductListState
    fun getProductDetailState() = productDetailState

    init {
        viewModelScope.launch {
            getProductList()
        }
    }

    suspend fun getProductList() = CoroutineScope(IO).launch {
        nextUrlLiveData.value?.let {
            productRepository.getProducts(it).body()?.let {
                it.productsResult?.let {
                    it.products.forEach {
                        println("Product ${it.name} ${it.followCount}")

                    }

                    lastProductListState.postValue(Resource.success(it))
                }
                /*   getProductDetail(it.productsResult?.products?.first()?.code?.toString())
                   it.productsResult?.horizontalProducts?.forEach {
                       println("PRODUCT : ${it.code} ${it.name}")
                   }*/
            }

        }
    }

    suspend fun getProductDetail(code: Int) = CoroutineScope(IO).launch {
        productRepository.getProductDetail(code).body()?.let {
            productDetailState.postValue(Resource.success(it.productItem))
            println("PRODUCT DETAIL : ${it.productItem.badge} ${it.productItem.mkName}")

        }

    }

    /* fun setCode() {
         lastCodeLiveData.postValue(lastProductListState.value?.products?.first()?.code?.toString())
         viewModelScope.launch { }
     }*/
}