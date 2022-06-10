package com.bingo.spadedemo.ui.second

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bingo.spadedemo.R

class ActionLayout @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet?=null,
    defStyleAttr: Int = 0
) :
    LinearLayout(context, attr, defStyleAttr) {

    init {

        inflate(context, R.layout.layout_action, this)

    }

    fun setItem(actionName: String) {
        findViewById<TextView>(R.id.tvName).text = actionName
        setOnClickListener {
            Toast.makeText(context, "点击了action", Toast.LENGTH_SHORT).show()
        }
    }

}