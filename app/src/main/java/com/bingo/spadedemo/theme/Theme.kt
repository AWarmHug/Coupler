package com.bingo.spadedemo.theme

import android.graphics.Color

open class Theme(val background: Background?) {

    companion object {
        val dark: Theme = Theme(Background(color = Color.WHITE))
        val light: Theme = Theme(Background(color = Color.BLACK))
    }

}