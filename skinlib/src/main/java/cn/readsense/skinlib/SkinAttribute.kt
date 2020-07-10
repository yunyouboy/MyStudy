package cn.readsense.skinlib

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import cn.readsense.skinlib.util.SkinResources
import cn.readsense.skinlib.util.SkinThemeUtils
import java.util.*

/**
 *Author:qyg
 *DATE:2020/7/10 11:47
 *Description：
 **/
class SkinAttribute {
    private val mAttributes: MutableList<String> = ArrayList()

    init {
        mAttributes.add("background")
        mAttributes.add("src")
        mAttributes.add("textColor")
        mAttributes.add("drawableLeft")
        mAttributes.add("drawableTop")
        mAttributes.add("drawableRight")
        mAttributes.add("drawableBottom")
    }

    //记录换肤需要操作的View与属性信息
    private val mSkinViews: MutableList<SkinView> = ArrayList()


    //记录下一个VIEW身上哪几个属性需要换肤textColor/src
    fun look(view: View, attrs: AttributeSet) {
        val mSkinPars: MutableList<SkinPair> = ArrayList()
        for (i in 0 until attrs.attributeCount) {
            //获得属性名  textColor/background
            val attributeName = attrs.getAttributeName(i)
            if (mAttributes.contains(attributeName)) {
                // #
                // ?722727272
                // @722727272
                val attributeValue = attrs.getAttributeValue(i)
                // 比如color 以#开头表示写死的颜色 不可用于换肤
                if (attributeValue.startsWith("#")) {
                    continue
                }
                var resId: Int
                // 以 ？开头的表示系统属性
                resId = if (attributeValue.startsWith("?")) {
                    val attrId = attributeValue.substring(1).toInt()
                    SkinThemeUtils.getResId(view.context, intArrayOf(attrId))[0]
                } else {
                    // 正常以 @ 开头
                    attributeValue.substring(1).toInt()
                }
                val skinPair = SkinPair(attributeName, resId)
                mSkinPars.add(skinPair)
            }
        }
        if (mSkinPars.isNotEmpty() || view is SkinViewSupport) {
            val skinView = SkinView(view, mSkinPars)
            // 如果选择过皮肤 ，调用 一次 applySkin 加载皮肤的资源
            skinView.applySkin()
            mSkinViews.add(skinView)
        }
    }


    /*
       对所有的view中的所有的属性进行皮肤修改
     */
    fun applySkin() {
        for (mSkinView in mSkinViews) {
            mSkinView.applySkin()
        }
    }

    internal class SkinView(var view: View, var skinPairs: List<SkinPair>) {//这个View的能被 换肤的属性与它对应的id 集合
        /**
         * 对一个View中的所有的属性进行修改
         */
        fun applySkin() {
            applySkinSupport() //自定义View换肤
            for (skinPair in skinPairs) {
                var left: Drawable? = null
                var top: Drawable? = null
                var right: Drawable? = null
                var bottom: Drawable? = null
                when (skinPair.attributeName) {
                    "background" -> {
                        val background = SkinResources.getInstance().getBackground(skinPair.resId)
                        //背景可能是 @color 也可能是 @drawable
                        if (background is Int) {
                            view.setBackgroundColor(background)
                        } else {
                            ViewCompat.setBackground(view, background as Drawable?)
                        }
                    }
                    "src" -> {
                        val background = SkinResources.getInstance().getBackground(skinPair.resId)
                        if (background is Int) {
                            (view as ImageView).setImageDrawable(ColorDrawable((background as Int?)!!))
                        } else {
                            (view as ImageView).setImageDrawable(background as Drawable?)
                        }
                    }
                    "textColor" -> (view as TextView).setTextColor(SkinResources.getInstance().getColorStateList(skinPair.resId))
                    "drawableLeft" -> left = SkinResources.getInstance().getDrawable(skinPair.resId)
                    "drawableTop" -> top = SkinResources.getInstance().getDrawable(skinPair.resId)
                    "drawableRight" -> right = SkinResources.getInstance().getDrawable(skinPair.resId)
                    "drawableBottom" -> bottom = SkinResources.getInstance().getDrawable(skinPair.resId)
                    else -> {
                    }
                }
                if (null != left || null != right || null != top || null != bottom) {
                    (view as TextView).setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
                }
            }
        }

        private fun applySkinSupport() {
            if (view is SkinViewSupport) {
                (view as SkinViewSupport).applySkin()
            }
        }

    }

    internal class SkinPair(
            //属性名
            var attributeName: String,
            //对应的资源id
            var resId: Int
    )
}