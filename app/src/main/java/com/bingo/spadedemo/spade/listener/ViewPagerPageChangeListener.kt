package com.bingo.spadedemo.spade.listener

import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.bingo.spadedemo.track.ViewTracker

class ViewPagerPageChangeListener(val viewPager: ViewPager):ViewPager.OnPageChangeListener {
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        Log.d("Track", "onPageSelected = $position")
        ViewTracker.getInstance().performClick(viewPager)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }
}