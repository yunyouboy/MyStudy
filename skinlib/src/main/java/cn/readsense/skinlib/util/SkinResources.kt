package cn.readsense.skinlib.util

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.text.TextUtils

/**
 *Author:qyg
 *DATE:2020/7/10 10:48
 *Description：
 **/
class SkinResources {
    private var mAppResources: Resources

    private var mSkinResources: Resources? = null
    private var mSkinPkgName: String? = null
    private var isDefaultSkin = true

    companion object {
        private var instance: SkinResources? = null

        fun init(context: Context) {
            if (null == instance) {
                synchronized(SkinResources::class) {
                    if (null == instance) {
                        instance = SkinResources(context)
                    }
                }
            }
        }

        fun getInstance(): SkinResources {
            return instance!!
        }
    }

    constructor(context: Context) {
        mAppResources = context.resources
    }

    fun applySkin(resources: Resources, pkgName: String) {
        mSkinResources = resources
        mSkinPkgName = pkgName
        isDefaultSkin = null == resources || TextUtils.isEmpty(pkgName)
    }

    fun reset() {
        mSkinResources = null
        mSkinPkgName = ""
        isDefaultSkin = true
    }

    /**
     * 1.通过原始app中的resId(R.color.XX)获取到自己的 名字
     * 2.根据名字和类型获取皮肤包中的ID
     */
    private fun getIdentifier(resId: Int): Int {
        if (isDefaultSkin) {
            return resId
        }
        val resName = mAppResources.getResourceEntryName(resId)
        val resType = mAppResources.getResourceTypeName(resId)
        return mSkinResources!!.getIdentifier(resName, resType, mSkinPkgName)
    }

    fun getColor(resId: Int): Int {
        if (isDefaultSkin) {
            return mAppResources.getColor(resId)
        }
        val skinId = getIdentifier(resId)
        if (0 == skinId) {
            return mAppResources.getColor(resId)
        }
        return mSkinResources!!.getColor(skinId)
    }

    fun getColorStateList(resId: Int): ColorStateList {
        if (isDefaultSkin) {
            return mAppResources.getColorStateList(resId)
        }
        val skinId = getIdentifier(resId)
        return if (skinId == 0) {
            mAppResources.getColorStateList(resId)
        } else mSkinResources!!.getColorStateList(skinId)
    }

    fun getDrawable(resId: Int): Drawable? {
        if (isDefaultSkin) {
            return mAppResources.getDrawable(resId)
        }
        //通过 app的resource 获取id 对应的 资源名 与 资源类型
        //找到 皮肤包 匹配 的 资源名资源类型 的 皮肤包的 资源 ID
        val skinId = getIdentifier(resId)
        return if (skinId == 0) {
            mAppResources.getDrawable(resId)
        } else mSkinResources!!.getDrawable(skinId)
    }


    /**
     * 可能是Color 也可能是drawable
     *
     * @return
     */
    fun getBackground(resId: Int): Any? {
        val resourceTypeName = mAppResources.getResourceTypeName(resId)
        return if ("color" == resourceTypeName) {
            getColor(resId)
        } else {
            // drawable
            getDrawable(resId)
        }
    }


}