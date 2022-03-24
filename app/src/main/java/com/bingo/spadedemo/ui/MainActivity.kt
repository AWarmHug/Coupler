package com.bingo.spadedemo.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bingo.spadedemo.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout = findViewById<TabLayout>(R.id.tab)
        val pager = findViewById<ViewPager2>(R.id.pager)

        pager.adapter = PagerAdapter(this)
        pager.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);


        TabLayoutMediator(tabLayout, pager) { tab, position ->

            when (position) {
                0 -> {
                    tab.setText("主页");
                }
                1 -> {
                    tab.setText("推荐");
                }
                2 -> {
                    tab.setText("我的");
                }
            }
        }.attach()

        MainActivity::class.java

    }

    fun play(message:String){
        Log.d("TAG", "play: message = ${message}")

    }

    class PagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    MainFragment()
                }
                1 -> {
                    ListFragment()
                }
                else -> {
                    MineFragment()
                }
            }
        }

    }


}