package com.example.materialdesign.coordinatorstudy.simplebehavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

class SampleHeaderBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<TextView>(context, attrs) {
    private var mOffsetTopAndBottom = 0
    private var mLayoutTop = 0

    override fun onLayoutChild(parent: CoordinatorLayout, child: TextView, layoutDirection: Int): Boolean {
        parent.onLayoutChild(child, layoutDirection)
        mLayoutTop = child.top
        return true
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: TextView, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return true
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: TextView, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        var consumedy = 0 // 记录我们消费的距离
        //dy是滚动,理解为画布不动，内容平移。
        //offsetTopAndBottom，理解为内容不懂，画布平移。
        //所以，dy值、offsetTopAndBottom值，正负相反
        var offset = mOffsetTopAndBottom - dy
        val minOffset = -getChildScrollRang(child)
        val maxOffset = 0
        offset = if (offset < minOffset) minOffset else if (offset > maxOffset) maxOffset else offset
        ViewCompat.offsetTopAndBottom(child, offset - (child.top - mLayoutTop))
        consumedy = mOffsetTopAndBottom - offset
        // 将本次滚动到的位置记录下来
        mOffsetTopAndBottom = offset
        consumed[1] = consumedy
    }

    // 获取childView最大可滑动距离
    private fun getChildScrollRang(childView: View): Int {
        return childView.height ?: 0
    }

}