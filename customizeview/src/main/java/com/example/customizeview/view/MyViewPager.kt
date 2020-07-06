package com.example.customizeview.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import kotlin.math.max

/**
 *Author:qyg
 *DATE:2020/7/6 16:25
 *Description：
 **/

class MyViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {
    constructor(context: Context) : this(context, null) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var maxHeight = 0
        for (index in 0 until childCount) {
            var childView = getChildAt(index)
            var childP = childView.layoutParams
            var childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingBottom + paddingTop, childP.height)
            childView.measure(widthMeasureSpec, childHeightMeasureSpec)
            maxHeight = max(maxHeight, childView.measuredHeight)
        }
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.EXACTLY))
    }
}