package com.cengcelil.productlistingapp.presentation.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import com.cengcelil.productlistingapp.R

/**
 * TODO: document your custom view class.
 */
class CustomRadioButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatRadioButton(context, attrs) {
    companion object {
        const val margin = 40
    }

    init {
        background = AppCompatResources.getDrawable(context, R.drawable.radio_buttons_selector)
        buttonDrawable = null
        setPadding(margin, margin, margin, margin)
        invalidate()

    }
}