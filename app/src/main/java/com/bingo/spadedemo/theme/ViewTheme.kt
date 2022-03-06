package com.bingo.spadedemo.theme

import android.view.View
import android.widget.Button


open class ViewTheme(val textColor: TextColor? = null, val background: Background? = null) :
    ThemeBinder {

    override fun binding(view: View) {

        textColor?.binding(view)

        background?.binding(view)

    }


}