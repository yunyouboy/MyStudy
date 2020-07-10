package cn.readsense.skinlib

import android.content.Context
import android.content.SharedPreferences

/**
 *Author:qyg
 *DATE:2020/7/10 15:24
 *Description：
 **/
class SkinPreference {

    private val SKIN_SHARED = "skins"
    private val KEY_SKIN_PATH = "skin-path"


    private var mPref: SharedPreferences

    companion object {
        @Volatile
        private var instance: SkinPreference? = null

        fun init(context: Context) {
            if (instance == null) {
                synchronized(SkinPreference::class.java) {
                    if (instance == null) {
                        instance = SkinPreference(context.applicationContext)
                    }
                }
            }
        }

        fun getInstance(): SkinPreference {
            return instance!!
        }
    }

    private constructor(context: Context) {
        mPref = context.getSharedPreferences(SKIN_SHARED, Context.MODE_PRIVATE)
    }

    fun setSkin(skinPath: String?) {
        mPref.edit().putString(KEY_SKIN_PATH, skinPath).apply()
    }

    fun reset() {
        mPref.edit().remove(KEY_SKIN_PATH).apply()
    }

    fun getSkin(): String? {
        return mPref.getString(KEY_SKIN_PATH, null)
    }
}