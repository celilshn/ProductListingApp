package com.cengcelil.basemvvmapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.cengcelil.productlistingapp.common.Status
import com.cengcelil.productlistingapp.data.remote.model.ProductItem
import com.cengcelil.productlistingapp.databinding.ProductDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var b: ProductDetailFragmentBinding
    lateinit var productItem: ProductItem

    override fun onAttach(context: Context) {
        super.onAttach(context)
        b = ProductDetailFragmentBinding.inflate(LayoutInflater.from(requireContext()))
        viewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var code = requireArguments().getInt("code")
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getProductDetail(code = code)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = b.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            getProductDetailState().observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> {
                        productItem = it.data!!.also {
                            updateUI(it)
                        }
                    }
                }
            }
        }
    }

    private fun updateUI(data: ProductItem) {
        with(b){
            txtBrandName.text = data.mkName
            txtProductName.text = data.productName
            ratingBar.rating = data.rating
        }

    }


}