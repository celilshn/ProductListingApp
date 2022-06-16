package com.cengcelil.basemvvmapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cengcelil.productlistingapp.common.Status
import com.cengcelil.productlistingapp.databinding.ProductListFragmentBinding
import com.cengcelil.productlistingapp.presentation.adapter.ProductListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var b: ProductListFragmentBinding
    private val productListAdapter = ProductListAdapter {
        it?.let {
            findNavController().navigate(
                ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                    it
                )
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        b = ProductListFragmentBinding.inflate(LayoutInflater.from(requireContext())).apply {
            with(rcyProductList) {
                adapter = productListAdapter
            }
        }
        viewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
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
            getProductListState().observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.ERROR -> {}
                    Status.SUCCESS -> {
                        it?.let {
                            productListAdapter.submitList(it.data!!.products.apply {
                                forEach {
                                    println("Product ${it.name} ${it.followCount}")
                                }
                            })
                        }

                    }
                }
            }
        }
    }


}