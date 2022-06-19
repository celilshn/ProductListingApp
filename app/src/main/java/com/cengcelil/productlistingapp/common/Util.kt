package com.cengcelil.productlistingapp.common

import android.view.View
import android.widget.ViewSwitcher
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

object Util {
    val format = DecimalFormat("##.##")
    const val BLANK = ""
    const val startUrl = "https://mocki.io/v1/59906f35-d5d5-40f7-8d44-53fd26eb3a05"
    val numberFormat: NumberFormat by
    lazy {
        NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 0
            currency = Currency.getInstance("TRY")
        }
    }
}

fun ViewSwitcher.switch(view: View) {
    if (nextView == view)
        showNext()
}