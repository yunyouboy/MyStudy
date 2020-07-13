package cn.readsense.skinapp.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import cn.readsense.skinapp.R
import cn.readsense.skinlib.SkinViewSupport
import cn.readsense.skinlib.util.SkinResources
import com.google.android.material.tabs.TabLayout


/**
 * @author Lance
 * @date 2018/3/12
 */
class MyTabLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TabLayout(context, attrs, defStyleAttr), SkinViewSupport {


    private var tabIndicatorColorResId: Int
    private var tabTextColorResId: Int

    override fun applySkin() {
        if (tabIndicatorColorResId != 0) {
            val tabIndicatorColor: Int = SkinResources.getInstance().getColor(tabIndicatorColorResId)
            setSelectedTabIndicatorColor(tabIndicatorColor)
        }
        if (tabTextColorResId != 0) {
            val tabTextColor: ColorStateList = SkinResources.getInstance().getColorStateList(tabTextColorResId)!!
            tabTextColors = tabTextColor
        }
    }

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.TabLayout, defStyleAttr, 0)
        tabIndicatorColorResId = a.getResourceId(R.styleable.TabLayout_tabIndicatorColor, 0)
        tabTextColorResId = a.getResourceId(R.styleable.TabLayout_tabTextColor, 0)
        a.recycle()
    }
}