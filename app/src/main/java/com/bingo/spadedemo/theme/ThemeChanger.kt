package com.bingo.spadedemo.theme

import android.view.View
import com.bingo.spadedemo.track.DefaultFinder

class ThemeChanger :Changer{

    override fun change(view: View) {
        //获取Activity name
        //根据Activity获取资源包
        val activity=DefaultFinder.getActivity(view)

        val position= DefaultFinder.getName(view)


    }
}