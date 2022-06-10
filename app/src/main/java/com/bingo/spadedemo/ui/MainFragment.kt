package com.bingo.spadedemo.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bingo.spadedemo.R
import com.bingo.spadedemo.theme.*
import com.bingo.spadedemo.ui.second.SecondActivity
import kotlin.random.Random

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.btnChangeTheme).setOnClickListener {
            //通过这种方式可以
            if (skin.value == null || skin.value == Skin.DARK) {
                skin.value = Skin.LIGHT
            } else {
                skin.value = Skin.DARK
            }
        }
        view.findViewById<Button>(R.id.btnToSecond).setOnClickListener {
            startActivity(Intent(context, SecondActivity::class.java))
        }
        view.findViewById<Button>(R.id.btnPlay).setOnClickListener {
            (requireActivity() as MainActivity).play("皮球")
        }
        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            UserUtils("").login(Random.nextInt().toString())
        }
        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            UserUtils("").logout()
        }

        view.findViewById<TextView>(R.id.bt2).setOnClickListener {
            val newSkin = skin.value ?: Skin.LIGHT
            val viewTheme =
                ViewTheme(
                    textColor = TextColor(textColor = Color.BLACK),
                    background = Background(url = "https://www.baidu.com/img/pc_9c5c85e6b953f1d172e1ed6821618b91.png")
                )
            newSkin.themes["bdeed52e7398c38977691652f3670007"] = viewTheme

            skin.value = newSkin


        }

    }


}