package com.cengcelil.basemvvmapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cengcelil.productlistingapp.R
import com.cengcelil.productlistingapp.common.Status
import com.cengcelil.productlistingapp.common.Util
import com.cengcelil.productlistingapp.data.remote.model.ProductItem
import com.cengcelil.productlistingapp.databinding.ProductDetailFragmentBinding
import com.cengcelil.productlistingapp.presentation.view.custom.CustomRadioButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.StringBuilder

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
        with(b) {
            txtBrandName.text = data.mkName
            txtProductName.text = data.productName
            ratingBar.rating = data.rating
            Glide.with(requireContext()).load(data.imageUrl).into(imageView)
            b.radioGroup.removeAllViewsInLayout()


            val radioButtons = data.storageOptions.map {
                CustomRadioButton(requireContext()).apply {
                    text = it
                    id = View.generateViewId()
                }
            }
            radioButtons.forEach {
                if (it == radioButtons.first())
                    it.isChecked = true
                radioGroup.addView(it)
            }
            radioGroup.children.forEach {
                it.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    marginEnd = CustomRadioButton.margin
                    marginStart = marginEnd
                }
            }
            txtBadge.text = data.badge
            txtSellerCount.text = StringBuilder().append(data.countOfPrices).append(" ").append(
                getString(
                    R.string.lowest_price_text,
                    if (data.freeShipping) getString(R.string.free_shipping) else ""
                )
            )
            txtProductPrice.text =
                Util.numberFormat.format(data.price) ?: Util.BLANK

            if (data.freeShipping)
                txtFreeShipping.text = getString(R.string.free_cargo)
            else
                txtFreeShipping.isVisible = false
            txtLastUpdated.text = StringBuilder().append(getString(R.string.last_updated))
                .append(" ${data.lastUpdate}")

        }

    }

}