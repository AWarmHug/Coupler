package com.bingo.spadedemo.theme

import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

class Background(
    @ColorInt val color: Int? = null,
    val url: String? = null,
    @DrawableRes val drawable: Int? = null
) : ThemeBinder {

    override fun binding(view: View) {
        if (color != null) {
            view.setBackgroundColor(color)
        }
        if (drawable != null) {
            view.setBackgroundResource(drawable)
        }
        if (url != null) {
        }
    }
}