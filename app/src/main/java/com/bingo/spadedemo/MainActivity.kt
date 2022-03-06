package com.bingo.spadedemo

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

            if (skin.value==null||skin.value == Skin.DARK) {
                skin.value = Skin.LIGHT
            } else {
                skin.value = Skin.DARK
            }
        }
    }


}