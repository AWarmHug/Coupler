package com.bingo.spadedemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bingo.spade.Spade
import com.bingo.spade.Tracker
import com.bingo.spadedemo.track.ViewTracker
import com.bingo.spadedemo.track.ViewTrace


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.bt).setOnClickListener {

        }
    }
}