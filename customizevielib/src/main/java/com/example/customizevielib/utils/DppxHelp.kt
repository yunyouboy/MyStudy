package com.example.customizevielib.utils

import android.content.res.Resources
import android.util.TypedValue


/**
 *Author:qyg
 *DATE:2020/7/15 16:36
 *Description：
 **/
fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
}

fun sp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, Resources.getSystem().displayMetrics)
}