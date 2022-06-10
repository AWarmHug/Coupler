package com.bingo.spadedemo.spade.listener

import android.util.Log
import androidx.viewpager.widget.ViewPager

class ViewPagerPageChangeListener:ViewPager.OnPageChangeListener {
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        Log.d("Track", "onPageSelected = $position")
    }

    override fun onPageScrollStateChanged(state: Int) {
    }
}