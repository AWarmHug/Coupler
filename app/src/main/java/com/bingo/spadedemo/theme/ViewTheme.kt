package com.bingo.spadedemo.theme

import android.content.res.Resources
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.R


/**
 * 针对ViewTheme需要注意的
 * 1、背景需要可以变换
 * 2、文字颜色 黑白变换
 * 3、支持Resource.Theme
 * 但是需要判断，先有的Theme是否为ViewTheme中设置的，
 * 如果是，切换主题，如果不是，不切换主题
 * 一般而言，跟随ViewTheme的控件为少数
 *
 */
open class ViewTheme constructor(
    val colorPrimary: Int? = null,
    val colorOnPrimary: Int? = null,

    val toolbarBackground: Background? = null,
    val textColor: TextColor? = null,
    val itemBackground: Background? = null,
    val background: Background? = null
) :
    ThemeBinder {

//    constructor(theme: Resources.Theme) : this() {
//        val typedArray =
//            theme.obtainStyledAttributes(R.styleable.AppCompatTheme)
//            val colorPrimary = typedArray.getColor(
//                com.google.android.material.R.styleable.AppCompatTheme_colorPrimary,
//                Color.WHITE
//            )
//        typedArray.recycle()
//    }

    init {


    }


    override fun binding(view: View) {

        if (view is Toolbar) {
            val background = toolbarBackground ?: Background(color = colorPrimary)
            background.binding(view)

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