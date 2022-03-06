package com.bingo.spadedemo.theme

import android.graphics.Color
import android.util.Log
import android.view.View
import com.bingo.spadedemo.track.DefaultFinder


class Skin(val common: ViewTheme? = null) : Changer {


    val themes = mutableMapOf<String, ViewTheme>()


    companion object {

        val DARK: Skin =
            Skin(ViewTheme( Color.WHITE, Background(color = Color.BLACK)))
        val LIGHT: Skin =
            Skin(ViewTheme( Color.BLACK, Background(color = Color.WHITE)))
    }

    override fun change(view: View) {
        //生成最合适的Theme
        Log.d("TAG", "TFrameLayout111: " + view.getId() + "sss=" + view)

        var viewTheme = themes[DefaultFinder.getName(view)]
        if (viewTheme == null) {

            var textColor: Int? = null
            var background: Background? = null
            common?.let {
//                if (view.id == android.R.id.content) {
                    background = it.background
//                }
                textColor = it.textColor
                viewTheme = ViewTheme(textColor, background = background)
            }

        }
        viewTheme?.change(view)


    }
}