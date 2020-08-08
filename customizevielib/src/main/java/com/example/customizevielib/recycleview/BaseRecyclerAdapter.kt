package com.example.customizevielib.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerAdapter<Bean, Binding : ViewDataBinding>
constructor(private val layoutRes: Int,
            private val onCellClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract val baseList: MutableList<Bean>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BaseViewHolder<Binding>(
                LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        )
    }

    override fun getItemCount(): Int = baseList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as BaseViewHolder<Binding>
        holder.binding?.root?.setOnClickListener {
            onCellClick(position)
        }
        bindData(holder.binding!!, position)
    }

    abstract fun bindData(binding: Binding, position: Int)

    fun replaceData(newData: MutableList<Bean>) {
        baseList.run {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    fun addData(bean: Bean) {
        baseList.add(bean)
        notifyItemInserted(baseList.size - 1)
    }

    fun addData(bean: Bean, position: Int) {
        baseList.add(0, bean)
        notifyDataSetChanged()
    }

    fun addDatas(datas: MutableList<Bean>) {
        baseList.addAll(datas)
        notifyItemRangeInserted(baseList.size - datas.size, datas.size)
    }

    fun removeData(position: Int): Bean {
        val bean = baseList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, baseList.size)
        return bean
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (toPosition >= 0 && toPosition < baseList.size) {
            Collections.swap(baseList, fromPosition, toPosition)
            notifyItemChanged(fromPosition, toPosition)
        }
    }
}