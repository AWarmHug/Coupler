package com.bingo.spadedemo.theme

import android.view.View
import android.widget.Button


open class ViewTheme(val textColor: Int? = null, val background: Background? = null) : ThemeBinder {

    override fun binding(view: View) {

        textColor?.let {
            if (view is Button) {
                view.setTextColor(it)
            }
        }
        background?.binding(view)
    }


}