package com.bingo.spadedemo.theme

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt

class TextColor(
    @ColorInt val textColor: Int? = null,
    @ColorInt val hintTextColor: Int? = null,
) : ThemeBinder {

    override fun binding(view: View) {
        if (view is TextView){
            textColor?.let {
                view.setTextColor(it)
            }
            hintTextColor?.let {
                view.setHintTextColor(it)
            }
        }

    }
}