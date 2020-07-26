package com.example.customizeview.toprecycleview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customizevielib.utils.dp2px

class StarDecoration : RecyclerView.ItemDecoration() {
    private var groupHeaderHeight: Float = 100f
    private lateinit var headPaint: Paint
    private lateinit var textPaint: Paint
    private lateinit var textRect: Rect

    init {
        groupHeaderHeight = dp2px(100f)

        headPaint = Paint()
        headPaint.apply {
            color = Color.RED
            isAntiAlias = true
            isDither = true
        }

        textPaint = Paint()
        textPaint.apply {
            textSize = 50f
            color = Color.WHITE
            isAntiAlias = true
            isDither = true
        }

        textRect = Rect()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.adapter is StarAdapter) {
            val starAdapter = parent.adapter as StarAdapter
            val position = parent.getChildAdapterPosition(view)
            val isGroupHeader: Boolean = starAdapter.isGroupHeader(position)
            if (isGroupHeader) {
                // 如果是头部，预留更大的地方
                outRect.set(0, groupHeaderHeight.toInt(), 0, 0)
            } else {
                // 4像素
                outRect.set(0, 4, 0, 0)
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (parent.adapter is StarAdapter) {
            val starAdapter = parent.adapter as StarAdapter

            val left = parent.paddingLeft
            val right: Int = parent.width - parent.paddingRight

            // 当前屏幕的item个数
            val count = parent.childCount
            for (index in 0 until count) {
                // 获取对应i的View（当前屏幕的第i个View）
                val view = parent.getChildAt(index)
                val position = parent.getChildAdapterPosition(view)
                val isGroupHeader: Boolean = starAdapter.isGroupHeader(position)
                if (isGroupHeader && view.top - groupHeaderHeight - parent.paddingTop > 0) {
                    // 头部
                    c.drawRect(left.toFloat(), view.top - groupHeaderHeight, right.toFloat(), view.top.toFloat(), headPaint)
                    val groupName: String = starAdapter.getGroupName(position)
                    textPaint.getTextBounds(groupName, 0, groupName.length, textRect)
                    c.drawText(groupName, left + 20.toFloat(), view.top - groupHeaderHeight / 2 + textRect.height() / 2.toFloat(), textPaint)
                } else if (view.top - groupHeaderHeight - parent.paddingTop > 0) {
                    // 分割线
                    c.drawRect(left.toFloat(), (view.top - 4).toFloat(), right.toFloat(), view.top.toFloat(), headPaint)
                }
            }
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        if (parent.adapter is StarAdapter) {

            val left = parent.paddingLeft
            val right: Int = parent.width - parent.paddingRight
            val top = parent.paddingTop

            val starAdapter = parent.adapter as StarAdapter
            // 返回可见区域内的第一个item的position(adapter的position)
            val linearLayoutManager = parent.layoutManager as LinearLayoutManager
            val position = linearLayoutManager.findFirstVisibleItemPosition()
            // 获取对应position的View
            val itemView = parent.findViewHolderForAdapterPosition(position)?.itemView!!

            // 当第二个是组的头部的时候
            val isGroupHeader: Boolean = starAdapter.isGroupHeader(position + 1)
            if (isGroupHeader) {
                // 头部
                var bottom = groupHeaderHeight.coerceAtMost((itemView.bottom - parent.paddingTop).toFloat())
                if (bottom < 0) bottom = 0f

                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), top + bottom.toFloat(), headPaint)
                val groupName: String = starAdapter.getGroupName(position)
                textPaint.getTextBounds(groupName, 0, groupName.length, textRect)

                c.drawText(groupName, left + 20.toFloat(), top + bottom / 2 + textRect.height().toFloat(), textPaint)
            } else {
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), top + groupHeaderHeight, headPaint)
                val groupName: String = starAdapter.getGroupName(position)
                textPaint.getTextBounds(groupName, 0, groupName.length, textRect)
                c.drawText(groupName, left + 20.toFloat(), top + groupHeaderHeight / 2 + (textRect.height() / 2).toFloat(), textPaint)
            }
        }
    }
}
