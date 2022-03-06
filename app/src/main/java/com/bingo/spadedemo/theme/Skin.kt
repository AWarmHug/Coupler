package com.bingo.spadedemo.theme

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.bingo.spadedemo.track.DefaultFinder


class Skin(val common: ViewTheme? = null) : ThemeBinder {


    val themes = mutableMapOf<String, ViewTheme>()


    companion object {

        val DARK: Skin =
            Skin(ViewTheme(Color.WHITE, Background(color = Color.BLACK)))
        val LIGHT: Skin =
            Skin(ViewTheme(Color.BLACK, Background(color = Color.WHITE)))
    }

    override fun binding(view: View) {
        //生成最合适的Theme
        Log.d("TAG", "TFrameLayout111: " + view.getId() + "sss=" + view)
        //获取Activity name
        //根据Activity获取资源包
        val activity=DefaultFinder.getActivity(view)

        val position= DefaultFinder.getName(view)

        var viewTheme = themes[DefaultFinder.getName(view)]
        if (viewTheme == null) {

            var textColor: Int? = null
            var background: Background? = null
            common?.let {
                if (view.parent is ViewGroup && (view.parent as ViewGroup).id == android.R.id.content) {
                    background = it.background
                }

                if (view.background == null) {
                    textColor = it.textColor
                }
                viewTheme = ViewTheme(textColor, background = background)
            }

        }
        if (view is ThemeChanger){
            view.onChange(viewTheme)
        }else {
            viewTheme?.binding(view)
        }


    }
}