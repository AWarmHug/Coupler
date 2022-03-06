package com.bingo.spadedemo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ContentFrameLayout
import com.bingo.spadedemo.spade.widget.TFrameLayout
import com.bingo.spadedemo.theme.Skin
import com.bingo.spadedemo.theme.ViewTheme
import com.bingo.spadedemo.theme.skin


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TAG", "onCreate: ${findViewById<FrameLayout>(android.R.id.content) is TFrameLayout}")

        findViewById<Button>(R.id.bt).setOnClickListener {
            Log.d("TAG", "onCreate: ")
            //通过这种方式可以
            theme.applyStyle(R.style.Theme_Spade_Blue, true)

            val typedArray =
                theme.obtainStyledAttributes(com.google.android.material.R.styleable.AppCompatTheme)

            val colorPrimary = typedArray.getColor(
                com.google.android.material.R.styleable.AppCompatTheme_colorPrimary,
                Color.WHITE
            )

            Log.d("TAG", "onCreate: ${colorPrimary==getColor(R.color.blue_500)}")


            typedArray.recycle()
//            if (skin.value==null||skin.value == Skin.DARK) {
//                skin.value = Skin.LIGHT
//            } else {
//                skin.value = Skin.DARK
//            }
        }
    }


}