package com.bingo.spadedemo.theme

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.bingo.spadedemo.R

class TextColor(
    @ColorInt val textColor: Int? = null,
    @ColorInt val hintTextColor: Int? = null,
) : ThemeBinder {

    override fun binding(view: View) {
        if (view is TextView) {
            textColor?.let {
                if (view.canChange()) {
                    view.setTextColor(it)
                }
            }
            hintTextColor?.let {
                view.setHintTextColor(it)
            }
        }

    }
}

private fun TextView.canChange(): Boolean {
    return textColors.defaultColor == ContextCompat.getColor(
        context,
        R.color._80_text_color_black
    ) ||
            textColors.defaultColor == ContextCompat.getColor(
        context,
        R.color._40_text_color_black
    ) ||
            textColors.defaultColor == ContextCompat.getColor(
        context,
        R.color._80_text_color_white
    ) ||
            textColors.defaultColor == ContextCompat.getColor(context, R.color._40_text_color_white)
}