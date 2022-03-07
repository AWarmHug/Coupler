package com.bingo.spadedemo.theme

import android.content.res.Resources
import android.view.View
import android.widget.Button
import com.google.android.material.R


open class ViewTheme constructor(val textColor: TextColor? = null, val background: Background? = null) :
    ThemeBinder {

    constructor(theme: Resources.Theme) : this() {
        val typedArray =
            theme.obtainStyledAttributes(R.styleable.AppCompatTheme)
//            val colorPrimary = typedArray.getColor(
//                com.google.android.material.R.styleable.AppCompatTheme_colorPrimary,
//                Color.WHITE
//            )

        typedArray.recycle()

    }


    override fun binding(view: View) {

        textColor?.binding(view)

        background?.binding(view)

    }


}