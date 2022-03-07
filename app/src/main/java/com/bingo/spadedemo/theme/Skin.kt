package com.bingo.spadedemo.theme

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.core.graphics.alpha
import com.bingo.spadedemo.track.DefaultFinder


class Skin(val common: ViewTheme? = null) : ThemeBinder {

    val themes = mutableMapOf<String, ViewTheme>()

    companion object {

        val DARK: Skin =
            Skin(
                ViewTheme(
                    colorPrimary=  Color.parseColor("#454141"),
                    colorOnPrimary = Color.parseColor("#FFFFFF"),
                    TextColor(Color.parseColor("#F6F6F6")),
                    Background(color = Color.parseColor("#000000")),
                    background = Background(color = Color.parseColor("#454141"))
                )
            )
        val LIGHT: Skin =
            Skin(
                ViewTheme(
                    colorPrimary=  Color.parseColor("#E8E8E8"),
                    colorOnPrimary = Color.parseColor("#000000"),
                    TextColor(Color.parseColor("#070707")),
                    Background(color = Color.parseColor("#FFFFFF")),
                    background = Background(color = Color.parseColor("#E8E8E8"))
                )
            )


    }

    override fun binding(view: View) {
        //生成最合适的Theme
        Log.d("TAG", "TFrameLayout111: " + view.getId() + "sss=" + view)
        //获取Activity name
        //根据Activity获取资源包
//        val activity=DefaultFinder.getActivity(view)
//        val position= DefaultFinder.getName(view)

        var viewTheme = themes[DefaultFinder.getName(view)]
        if (viewTheme == null) {
            var textColor: TextColor? = null
            var itemBackground: Background? = null
            var background: Background? = null
            common?.let {
                if (view.parent is ViewGroup && (view.parent as ViewGroup).id == android.R.id.content) {
                    background = it.background
                }

                if (view is CardView && view.background != null) {
                    itemBackground = it.itemBackground
                }

                if (view.background == null || view is EditText) {
                    textColor = it.textColor
                }
                viewTheme = ViewTheme(it.colorPrimary,it.colorOnPrimary, textColor, itemBackground,background = background)
            }

        }

        if (view is ThemeChanger) {
            view.onChange(viewTheme)
        } else {
            viewTheme?.binding(view)
        }

    }
}