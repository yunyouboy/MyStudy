package com.example.materialdesign.comm

import android.content.Context
import android.graphics.Bitmap
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommonRecyclerHolder private constructor(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val views: SparseArray<View> = SparseArray(8)
    fun getViews(): SparseArray<View> {
        return views
    }

    /**
     * 通过view的id获取对应的控件，如果没有则加入views中
     * @param viewId 控件的id
     * @return 返回一个控件
     */
    private fun <T : View> getView(viewId: Int): T {
        var view = views.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }

    /**
     * 设置字符串
     */
    fun setText(viewId: Int, text: String): CommonRecyclerHolder {
        val tv = getView<TextView>(viewId)
        tv.text = text
        return this
    }

    fun setTextColor(viewId: Int, color: Int): CommonRecyclerHolder {
        val textView = getView<TextView>(viewId)
        textView.setTextColor(color)
        return this
    }

    /**
     * 设置图片
     */
    fun setImageResource(viewId: Int, drawableId: Int): CommonRecyclerHolder {
        val iv = getView<ImageView>(viewId)
        iv.setImageResource(drawableId)
        return this
    }

    /**
     * 设置图片
     */
    fun setImageBitmap(viewId: Int, bitmap: Bitmap?): CommonRecyclerHolder {
        val iv = getView<ImageView>(viewId)
        iv.setImageBitmap(bitmap)
        return this
    }

    fun setOnRecyclerItemClickListener(viewId: Int, listener: View.OnClickListener?): CommonRecyclerHolder {
        val view = getView<View>(viewId)
        view.setOnClickListener(listener)
        return this
    }

    companion object {
        /**
         * 取得一个RecyclerHolder对象
         * @param context 上下文
         * @param itemView 子项
         * @return 返回一个RecyclerHolder对象
         */
        fun getRecyclerHolder(context: Context, itemView: View): CommonRecyclerHolder {
            return CommonRecyclerHolder(context, itemView)
        }
    }
}