package com.cengcelil.basemvvmapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.cengcelil.productlistingapp.common.Status
import com.cengcelil.productlistingapp.common.switch
import com.cengcelil.productlistingapp.databinding.ProductListFragmentBinding
import com.cengcelil.productlistingapp.presentation.adapter.HorizontalProductListAdapter
import com.cengcelil.productlistingapp.presentation.adapter.VerticalProductListAdapter
import com.cengcelil.productlistingapp.presentation.view.custom.GridSpacingItemDecoration
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var b: ProductListFragmentBinding
    private val productListAdapter = VerticalProductListAdapter { itemCallback(it) }
    private val horizontalProductListAdapter =
        HorizontalProductListAdapter { itemCallback(it) }

    private fun itemCallback(code: Int) = findNavController().navigate(
        ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
            code
        )
    )


    override fun onAttach(context: Context) {
        super.onAttach(context)
        b = ProductListFragmentBinding.inflate(LayoutInflater.from(requireContext())).apply {
            with(rcyProductList) {
                adapter = productListAdapter
                addItemDecoration(GridSpacingItemDecoration(2, 24, true))
            }
            with(pagerProductListItem) {
                adapter = horizontalProductListAdapter
                TabLayoutMediator(tablayout, pagerProductListItem)
                { _, _ -> }.attach()
            }
        }
        viewModel = ViewModelProvider(requireActivity())[ProductViewModel::
        class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = b.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        lifecycleScope.launch {
            viewModel.listData.collectLatest {
                productListAdapter.submitData(it)
                b.switcherVerticalList.switch(b.rcyProductList)
            }
        }
    }

    private fun setupObservers() {
        with(viewModel) {
            getProductListState().observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.LOADING -> {
                        b.switcherHorizontalList.switch(b.shimmerHorizontalList)

                    }
                    Status.SUCCESS -> {
                        it?.let {
                            horizontalProductListAdapter.submitList(it.data!!.horizontalProducts)
                            b.switcherHorizontalList.switch(b.layoutHorizontalList)

                        }
                    }
                }
            }
        }
    }


}