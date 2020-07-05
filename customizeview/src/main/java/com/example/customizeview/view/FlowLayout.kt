package com.example.customizeview.view

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import kotlin.math.max


class FlowLayout constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
        ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    private val TAG: String = FlowLayout::class.simpleName.toString()
    private val mHorizontalSpacing: Int = dp2px(16)
    private val mVerticalSpacing: Int = dp2px(8)

    private var allLines: MutableList<ArrayList<View>> = ArrayList()
    private var lineHeights: MutableList<Int> = ArrayList<Int>()

    constructor(context: Context) : this(context, null) {

    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        clearMeasureParams()

        var lineViews: MutableList<View> = ArrayList<View>()
        var lineWidthUsed: Int = 0
        var lineHeight: Int = 0

        var parentNeededWidth: Int = 0
        var parentNeededHeight: Int = 0

        val selfWidth = MeasureSpec.getSize(widthMeasureSpec)
        val selfHeight = MeasureSpec.getSize(heightMeasureSpec)

        var childCount = childCount
        var paddingLeft = paddingLeft
        var paddingTop = paddingTop
        var paddingRight = paddingRight
        var paddingBottom = paddingBottom


        for (index in 0 until childCount) {
            var childView = getChildAt(index)
            var childP = childView.layoutParams
            if (childView.visibility != View.GONE) {
                //将layoutParams转变成为 measureSpec
                val childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childP.width)
                val childHeightMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingTop + paddingBottom, childP.height)
                childView.measure(childWidthMeasureSpec, childHeightMeasureSpec)
                //获取子View的度量宽高
                val childMeasuredWidth = childView.measuredWidth
                val childMeasuredHeight = childView.measuredHeight
                //如果需要换行
                if (selfWidth < childMeasuredWidth + lineWidthUsed + mHorizontalSpacing) {
                    //一旦换行，我们就可以判断当前行需要的宽和高了，所以此时要记录下来
                    allLines.add(lineViews as ArrayList<View>)
                    lineHeights.add(lineHeight)

                    parentNeededHeight += mVerticalSpacing + lineHeight
                    parentNeededWidth = max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing)

                    lineViews = ArrayList<View>()
                    lineWidthUsed = 0
                    lineHeight = 0
                }
                // view 是分行layout的，所以要记录每一行有哪些view，这样可以方便layout布局
                lineViews.add(childView)
                //每行都会有自己的宽和高
                lineWidthUsed += childMeasuredWidth + mHorizontalSpacing
                lineHeight = max(lineHeight, childMeasuredHeight)

                //处理最后一行数据
                if (index == childCount - 1) {
                    allLines.add(lineViews as ArrayList<View>)
                    lineHeights.add(lineHeight)
                    parentNeededHeight += mVerticalSpacing + lineHeight
                    parentNeededWidth = max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing)
                }
            }
        }
        //再度量自己,保存
        //根据子View的度量结果，来重新度量自己ViewGroup
        // 作为一个ViewGroup，它自己也是一个View,它的大小也需要根据它的父亲给它提供的宽高来度量
        var widthMode: Int = MeasureSpec.getMode(widthMeasureSpec)
        var heightMode: Int = MeasureSpec.getMode(heightMeasureSpec)
        var realWidth = if (widthMode == MeasureSpec.EXACTLY) selfWidth else parentNeededWidth
        var realHeight = if (heightMode == MeasureSpec.EXACTLY) selfHeight else parentNeededHeight
        setMeasuredDimension(realWidth, realHeight)
    }

    private fun clearMeasureParams() {
        allLines.clear()
        lineHeights.clear()
    }

    override fun onLayout(change: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var curL = paddingLeft
        var curT = paddingTop

        for (index in 0 until allLines.size) {
            val lineHeight = lineHeights[index]
            val lineViews = allLines[index]
            for (indexInner in 0 until lineViews.size) {
                val view = lineViews[indexInner]
                var left = curL
                var top = curT
                var right = left + view.measuredWidth
                var bottom = top + view.measuredHeight
                view.layout(left, top, right, bottom)
                curL = right + mHorizontalSpacing
            }
            curL = paddingLeft
            curT += lineHeight + mVerticalSpacing
        }
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), Resources.getSystem().displayMetrics).toInt()
    }
}