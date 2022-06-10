package com.bingo.spadedemo.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bingo.spadedemo.R
import com.bingo.spadedemo.databinding.FragmentSecondBinding
import com.bingo.spadedemo.databinding.FragmentSecondListItemBinding
import com.bingo.spadedemo.ui.BaseFragment
import com.bumptech.glide.Glide

class SecondFragment : BaseFragment() {

    lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = ListAdapter()
        binding.list.layoutManager = LinearLayoutManager(view.context)
    }

}

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentSecondListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = "这是标题 $position"
            tvTitle.setOnClickListener {
                Toast.makeText(holder.itemView.context, "点击了第${position}个标题", Toast.LENGTH_SHORT)
                    .show()
            }

            tvContent.setOnClickListener {
                Toast.makeText(holder.itemView.context, "点击了第${position}个内容", Toast.LENGTH_SHORT)
                    .show()
            }
//            linearAction.addView()

            Glide.with(ivContent)
                .load("https://pics0.baidu.com/feed/5bafa40f4bfbfbedaf5a238f21f9813caec31fd5.jpeg")
                .into(ivContent)
            if (linearAction.childCount != 0) {
                linearAction.removeAllViews()
            }
            for (index in 0..3) {
                val actionLayout = ActionLayout(linearAction.context)
                actionLayout.setItem("操作$index")
                linearAction.addView(actionLayout)
            }

        }
    }

    override fun getItemCount(): Int {
        return 30
    }


    class ViewHolder(val binding: FragmentSecondListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}