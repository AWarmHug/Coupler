package com.bingo.spadedemo.ui.second

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bingo.spadedemo.R
import com.bingo.spadedemo.databinding.ActivitySecondBinding
import com.bingo.spadedemo.ui.ListFragment

class SecondActivity : AppCompatActivity() {

    val binding: ActivitySecondBinding by lazy {
        return@lazy DataBindingUtil.setContentView(this, R.layout.activity_second)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            pager.adapter = PagerAdapter(supportFragmentManager)
            tabLayout.setupWithViewPager(pager)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {


    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SecondFragment()
            }
            1 -> {
                ListFragment()
            }
            2 -> {
                DialogFragment()
            }
            else -> {
                SecondFragment()
            }
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "title$position"
    }

}