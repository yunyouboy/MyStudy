package com.example.materialdesign.coordinator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.coordinator.adapter.RecyclerItemAdapter.ItemHolder

class RecyclerItemAdapter : RecyclerView.Adapter<ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_layout, parent, false)
        return ItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {}
    override fun getItemCount(): Int {
        return 10
    }

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}