package com.bingo.spadedemo.theme

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

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
            Glide.with(view).asDrawable().load(url).into(object :SimpleTarget<Drawable>(){
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    view.background=resource
                }

            })
        }
    }
}