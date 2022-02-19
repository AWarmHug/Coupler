package com.bingo.spadedemo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bingo.spade.Spade
import com.bingo.spade.ViewTracker
import com.bingo.spadedemo.track.DefaultViewFinder
import com.bingo.spadedemo.track.DefaultViewTracker
import com.bingo.spadedemo.track.ViewTrace


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewTracker: ViewTracker<ViewTrace> = DefaultViewTracker(DefaultViewFinder())
        Spade.init(viewTracker)
        findViewById<Button>(R.id.bt).setOnClickListener {

        }
    }
}