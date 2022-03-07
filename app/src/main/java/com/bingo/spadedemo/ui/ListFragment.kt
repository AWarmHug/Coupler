package com.bingo.spadedemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bingo.spadedemo.R

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = view.findViewById<RecyclerView>(R.id.list)
        list.adapter = ItemAdapter()
        list.layoutManager = LinearLayoutManager(context)


    }


}

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            0 -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_list_item, parent, false)
                return ViewHolder(itemView)
            }
            else -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_list_item2, parent, false)
                return ViewHolder(itemView)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {


        }

    }

}