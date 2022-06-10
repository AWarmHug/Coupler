package com.bingo.spadedemo.spade.listener

import android.util.Log
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        Log.d("Track", "onScrolled: dx = $dx , dy = $dy")
    }
}