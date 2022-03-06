package com.bingo.spadedemo.theme

import android.view.View
import android.widget.TextView


open class ViewTheme(val textColor: Int? = null, val background: Background? = null) : Changer {

    override fun change(view: View) {

        textColor?.let {
            if (view is TextView) {
                view.setTextColor(it)
            }
        }
        background?.change(view)
    }


}