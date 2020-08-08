package com.example.customizeview.slidecardview

import com.example.customizevielib.recycleview.BaseRecyclerAdapter
import com.example.customizeview.R
import com.example.customizeview.databinding.SlideViewItemBinding

class SlideCardAdapter(override var baseList: MutableList<SlideCardBean>, var onClick: (Int) -> Unit) : BaseRecyclerAdapter<SlideCardBean, SlideViewItemBinding>(R.layout.rv_item_slide_card, onClick) {
    override fun bindData(binding: SlideViewItemBinding, position: Int) {
        binding.slideCardBean = baseList[position]
    }
}