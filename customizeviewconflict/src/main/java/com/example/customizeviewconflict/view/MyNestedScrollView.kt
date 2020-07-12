package com.example.customizeviewconflict.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.core.widget.NestedScrollView.OnScrollChangeListener
import androidx.recyclerview.widget.RecyclerView
import com.example.customizevielib.utils.FlingHelper

@RequiresApi(Build.VERSION_CODES.M)
class MyNestedScrollView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : NestedScrollView(context, attrs, defStyleAttr) {
    private val TAG: String = MyNestedScrollView::class.simpleName.toString()
    private lateinit var contentView: ViewGroup
    lateinit var topView: ViewGroup

    //记录当前滑动的y轴加速度
    private var velocityY: Int = 0

    //用于判断RecyclerView是否在fling
    var isStartFling = false

    var totalDy: Int = 0

    private val mFlingHelper: FlingHelper = FlingHelper(context)

    init {
        setOnScrollChangeListener(OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (isStartFling) {
                totalDy = 0
                isStartFling = false
            }
            if (scrollY == 0) {
                Log.e(TAG, "TOP SCROLL")
                // refreshLayout.setEnabled(true);
            }
            if (scrollY == getChildAt(0).measuredHeight - v.measuredHeight) {
                Log.e(TAG, "BOTTOM SCROLL")
                dispatchChildFling()
            }
            //在RecyclerView fling情况下，记录当前RecyclerView在y轴的偏移
            //在RecyclerView fling情况下，记录当前RecyclerView在y轴的偏移
            totalDy += scrollY - oldScrollY
        })
    }

    private fun dispatchChildFling() {
        if (velocityY != 0) {
            val splineFlingDistance = mFlingHelper.getSplineFlingDistance(velocityY)
            if (splineFlingDistance > totalDy) {
                childFling(mFlingHelper.getVelocityByDistance(splineFlingDistance - java.lang.Double.valueOf(totalDy.toDouble())))
            }
        }
        totalDy = 0
        velocityY = 0
    }

    private fun childFling(velY: Int) {
        val childRecyclerView: RecyclerView? = getChildRecyclerView(contentView)
        childRecyclerView?.fling(0, velY)
    }

    private fun getChildRecyclerView(viewGroup: ViewGroup): RecyclerView? {
        for (i in 0 until viewGroup.childCount) {
            val view = viewGroup.getChildAt(i)
            if (view is RecyclerView && view.javaClass == RecyclerView::class.java) {
                return viewGroup.getChildAt(i) as RecyclerView
            } else if (viewGroup.getChildAt(i) is ViewGroup) {
                val childRecyclerView: ViewGroup? = getChildRecyclerView((viewGroup.getChildAt(i) as ViewGroup))
                if (childRecyclerView is RecyclerView) {
                    return childRecyclerView
                }
            }
            continue
        }
        return null
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        contentView = getChildAt(0) as ViewGroup
        topView = contentView.getChildAt(0) as ViewGroup
        contentView = contentView.getChildAt(1) as ViewGroup

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val layoutParams = contentView.layoutParams
        layoutParams.height = measuredHeight
        contentView.layoutParams = layoutParams
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        if (dy > 0 && scrollY < topView.measuredHeight) {
            scrollBy(0, dy)
            consumed[1] = dy
        }
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        if (dy > 0 && scrollY < topView.measuredHeight) {
            scrollBy(0, dy)
            consumed[1] = dy
        }
    }

    override fun fling(velocityY: Int) {
        super.fling(velocityY)
        if (velocityY <= 0) {
            this.velocityY = 0
        } else {
            isStartFling = true
            this.velocityY = velocityY
        }
    }
}