package com.example.customizevielib.recycleview

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<Binding : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: Binding? by lazy {
        DataBindingUtil.bind<Binding>(itemView)
    }
}