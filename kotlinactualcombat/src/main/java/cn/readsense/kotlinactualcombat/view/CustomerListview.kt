package cn.readsense.kotlinactualcombat.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView

/**
 *Author:qyg
 *DATE:2020/7/1 13:41
 *Description：
 **/
class CustomerListview @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ListView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val exHeightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE.shr(2), MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, exHeightMeasureSpec)
    }
}