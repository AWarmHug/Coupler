package com.bingo.spadedemo.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.bingo.spadedemo.R
import com.bingo.spadedemo.databinding.FragmentDialogBinding
import com.bingo.spadedemo.databinding.FragmentSecondBinding
import com.bingo.spadedemo.ui.BaseFragment

class DialogFragment : BaseFragment() {
    lateinit var binding: FragmentDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDialogBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bt.setOnClickListener {
            val alertDialog= AlertDialog.Builder(requireContext())
                .setTitle("标题")
                .setMessage("这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容")
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .show()
        }
    }




}