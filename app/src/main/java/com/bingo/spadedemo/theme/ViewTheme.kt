package com.bingo.spadedemo.theme

import android.content.res.Resources
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.R


/**
 * 针对Resource.Theme定制一套
 */
open class ViewTheme constructor(
    val colorPrimary: Int? = null,
    val colorOnPrimary: Int? = null,

    val textColor: TextColor? = null,
    val itemBackground: Background? = null,
    val background: Background? = null
) :
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

        if (view is Toolbar){
            colorPrimary?.let {
                view.setBackgroundColor(it)
            }
            colorOnPrimary?.let {
                view.setTitleTextColor(it)
            }
            return
        }

        textColor?.binding(view)

        if (itemBackground != null) {
            itemBackground.binding(view)
        } else {
            background?.binding(view)
        }

    }


}