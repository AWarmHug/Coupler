package com.bingo.spadedemo.theme

import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.*

class Background(
    @ColorInt val color: Int? = null,
    val url: String? = null,
    @DrawableRes val drawable: Int? = null
) : Changer {

    override fun change(view: View) {
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