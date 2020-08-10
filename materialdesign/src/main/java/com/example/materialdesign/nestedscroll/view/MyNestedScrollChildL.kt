package com.example.materialdesign.nestedscroll.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.core.view.NestedScrollingChild
import androidx.core.view.NestedScrollingChildHelper
import androidx.core.view.ViewCompat

class MyNestedScrollChildL @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : LinearLayout(context, attrs, defStyleAttr), NestedScrollingChild {

    companion object {
        private const val Tag: String = "Zero"
    }

    private var helper: NestedScrollingChildHelper = NestedScrollingChildHelper(this)

    init {
        helper.isNestedScrollingEnabled = true
    }

    private var realHeight = 0
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        realHeight = 0
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.UNSPECIFIED)
            measureChild(view, widthMeasureSpec, heightMeasureSpec)
            Log.i("child: onMeasure", "getMeasuredHeight: " + view.measuredHeight)
            realHeight += view.measuredHeight
        }
        Log.i("child: onMeasure", "realHeight: $realHeight")
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
    }

    /**
     * 是否可以嵌套滑动
     *
     * @return
     */
    override fun isNestedScrollingEnabled(): Boolean {
        Log.i(Tag, "isNestedScrollingEnabled")
        return helper.isNestedScrollingEnabled
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        helper.isNestedScrollingEnabled = enabled
        Log.i(Tag, "setNestedScrollingEnabled:$enabled")
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return helper.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
        Log.i(Tag, "stopNestedScroll")
        helper.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        Log.i(Tag, "hasNestedScrollingParent")
        return helper.hasNestedScrollingParent()
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        Log.i(Tag, "dispatchNestedScroll:dxConsumed:" + dxConsumed + "," +
                "dyConsumed:" + dyConsumed + ",dxUnconsumed:" + dxUnconsumed + ",dyUnconsumed:" +
                dyUnconsumed + ",offsetInWindow:" + offsetInWindow)
        return helper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return helper.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        return helper.dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
        Log.i(Tag, "dispatchNestedPreScroll:dx" + dx + ",dy:" + dy + ",consumed:" + consumed?.get(1) + ",offsetInWindow:" + offsetInWindow)
        return helper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    private var mLastTouchX = 0
    private var mLastTouchY = 0
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val consumed = IntArray(2)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mLastTouchY = (event.rawY + .5f).toInt()
                var nestedScrollAxis = ViewCompat.SCROLL_AXIS_NONE
                nestedScrollAxis = nestedScrollAxis or ViewCompat.SCROLL_AXIS_VERTICAL
                startNestedScroll(nestedScrollAxis)
            }
            MotionEvent.ACTION_MOVE -> {
                val x = (event.rawX + .5f).toInt()
                val y = (event.rawY + .5f).toInt()
                val dx = mLastTouchX - x
                var dy = mLastTouchY - y
                mLastTouchX = x
                mLastTouchY = y
                //consumed[0] = dx;
                //consumed[1] = dy;
                if (dispatchNestedPreScroll(dx, dy, consumed, null)) {
                    Log.i("onMeasure", "dy: " + dy + ", cosumed: " + consumed[1])
                    dy -= consumed[1]
                    if (dy == 0) {
                        Log.i("onMeasure", "dy: $dy")
                        return true
                    }
                } else {
                    Log.i("onMeasure", "scrollBy: $dy")
                    scrollBy(0, dy)
                }
            }
        }
        return true
    }

    override fun scrollTo(x: Int, y: Int) {
        var y = y
        Log.i("onMeasure", "y: " + y + ", getScrollY: " + scrollY + ", height: " + height + ", realHeight: " + realHeight + ", -- " + (realHeight - height))
        if (y < 0) {
            y = 0
        }
        if (y > realHeight) {
            y = realHeight
        }
        if (y != scrollY) {
            Log.e("onMeasure", "scrollTo: $y")
            super.scrollTo(x, y)
        }
    }
}