package com.example.customizeviewconflict.view

import androidx.databinding.ViewDataBinding
import com.example.customizevielib.recycleview.BaseRecyclerAdapter
import com.example.customizeviewconflict.R
import com.example.customizeviewconflict.databinding.TitleListCellBinding
import com.example.customizeviewconflict.entity.TitleBean

class RecyclerAdapter(private val dataList: MutableList<TitleBean>, onCellClick: (Int) -> Unit) :
        BaseRecyclerAdapter<TitleBean, TitleListCellBinding>(R.layout.title_view, onCellClick) {

    override val baseList: MutableList<TitleBean> = dataList

    override fun bindData(binding: TitleListCellBinding, position: Int) {
        binding.title = baseList[position]
    }

}