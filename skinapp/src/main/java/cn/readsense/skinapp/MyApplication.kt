package cn.readsense.skinapp

import android.app.Application
import cn.readsense.skinlib.SkinManager

/**
 * @author Lance
 * @date 2018/3/8
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SkinManager.init(this)
    }
}