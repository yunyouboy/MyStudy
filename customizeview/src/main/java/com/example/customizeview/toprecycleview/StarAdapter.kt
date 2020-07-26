package com.example.customizeview.toprecycleview

import com.example.customizevielib.recycleview.BaseRecyclerAdapter
import com.example.customizeview.R
import com.example.customizeview.databinding.RecycleViewItemBinding

class StarAdapter(override val baseList: MutableList<Star>, onItemClick: (Int) -> Unit) : BaseRecyclerAdapter<Star, RecycleViewItemBinding>(R.layout.rv_item_star, onItemClick) {
    override fun bindData(binding: RecycleViewItemBinding, position: Int) {
        binding.startName = baseList[position]
    }

    internal fun isGroupHeader(position: Int): Boolean {
        return when (position) {
            0 -> true
            else -> return baseList[position].groupName != baseList[position - 1].groupName
        }

    }

    internal fun getGroupName(position: Int): String = baseList[position].groupName
}