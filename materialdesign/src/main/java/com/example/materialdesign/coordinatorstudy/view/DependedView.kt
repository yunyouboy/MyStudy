package com.example.materialdesign.coordinatorstudy.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.view.ViewCompat
import kotlin.math.abs

class DependedView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var mLastX = 0f
    private var mLastY = 0f
    private val mDragSlop: Int = ViewConfiguration.get(context).scaledTouchSlop//最小滑动距离

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mLastX = event.x
                mLastY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                val dx = (event.x - mLastX).toInt()
                val dy = (event.y - mLastY).toInt()
                if (abs(dx) > mDragSlop || abs(dy) > mDragSlop) {
                    ViewCompat.offsetTopAndBottom(this, dy)
                    ViewCompat.offsetLeftAndRight(this, dx)
                }
                mLastX = event.x
                mLastY = event.y
            }
            else -> {
            }
        }
        return true
    }

}