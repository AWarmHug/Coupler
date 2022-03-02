package com.bingo.spadedemo

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bingo.spadedemo.theme.Theme
import com.bingo.spadedemo.theme.themes


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.bt).setOnClickListener {


            val skin=

            Log.d("TAG", "onCreate: ")
            if (themes.value == null || themes.value == Theme.dark) {
                themes.value = Theme.light
            } else {
                themes.value = Theme.dark
            }
        }
    }


}