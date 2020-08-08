package com.example.materialdesign.comm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class CommonRecyclerAdapter<T>(//上下文
        private val context: Context, //数据源
        private val list: MutableList<T>, //布局id
        private val itemLayoutId: Int) : RecyclerView.Adapter<CommonRecyclerHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)//布局器
    private val isScrolling = false //是否在滚动
    private var listener: OnItemClickListener? = null//点击事件监听器
    private var longClickListener: OnItemLongClickListener? = null//长按监听器
    private var recyclerView: RecyclerView? = null

    //在RecyclerView提供数据的时候调用
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    /**
     * 定义一个点击事件接口回调
     */
    interface OnItemClickListener {
        open fun onItemClick(parent: RecyclerView, view: View, position: Int)
    }

    interface OnItemLongClickListener {
        open fun onItemLongClick(parent: RecyclerView, view: View, position: Int): Boolean
    }

    /**
     * 插入一项
     *
     * @param item
     * @param position
     */
    fun insert(item: T, position: Int) {
        list.add(position, item)
        notifyItemInserted(position)
    }

    /**
     * 删除一项
     *
     * @param position 删除位置
     */
    fun delete(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonRecyclerHolder {
        val view = inflater.inflate(itemLayoutId, parent, false)
        return CommonRecyclerHolder.Companion.getRecyclerHolder(context, view)
    }

    override fun onBindViewHolder(holder: CommonRecyclerHolder, position: Int) {
        holder.itemView.setOnClickListener { view ->
            if (listener != null && view != null && recyclerView != null) {
                val position = recyclerView!!.getChildAdapterPosition(view)
                listener!!.onItemClick(recyclerView!!, view, position)
            }
        }
        holder.itemView.setOnLongClickListener(OnLongClickListener { view ->
            if (longClickListener != null && view != null && recyclerView != null) {
                val position = recyclerView!!.getChildAdapterPosition(view)
                longClickListener!!.onItemLongClick(recyclerView!!, view, position)
                return@OnLongClickListener true
            }
            false
        })
        convert(holder, list.get(position), position, isScrolling)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    fun setOnItemLongClickListener(longClickListener: OnItemLongClickListener?) {
        this.longClickListener = longClickListener
    }

    /**
     * 填充RecyclerView适配器的方法，子类需要重写
     *
     * @param holder      ViewHolder
     * @param item        子项
     * @param position    位置
     * @param isScrolling 是否在滑动
     */
    abstract fun convert(holder: CommonRecyclerHolder?, item: T?, position: Int, isScrolling: Boolean)

}