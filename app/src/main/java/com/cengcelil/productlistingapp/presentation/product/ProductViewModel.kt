package com.cengcelil.basemvvmapp

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cengcelil.productlistingapp.ProductListResult
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
    private var lastCodeLiveData =
        MutableLiveData<String?>("{code}")
    private var lastProductList =
        MutableLiveData<ProductListResult?>()

    init {
        viewModelScope.launch {
            getProductList()
        }
    }

    suspend fun getProductList() = CoroutineScope(IO).launch {
        nextUrlLiveData.value?.let {
            productRepository.getProducts(it).body()?.let {
                lastProductList.postValue(it.productsResult)
                getProductDetail(it.productsResult?.products?.first()?.code?.toString())
                it.productsResult?.horizontalProducts?.forEach {
                    println("PRODUCT : ${it.code} ${it.name}")
                }
            }

        }
    }

    suspend fun getProductDetail(code: String?) = CoroutineScope(IO).launch {
        code?.let {
            productRepository.getProductDetail(it).body()?.let {
                it.productItem?.let {
                    println("PRODUCT : ${it.badge} ${it.mkName}")

                }
            }
        }

    }

    fun setCode() {
        lastCodeLiveData.postValue(lastProductList.value?.products?.first()?.code?.toString())
        viewModelScope.launch { }
    }
}