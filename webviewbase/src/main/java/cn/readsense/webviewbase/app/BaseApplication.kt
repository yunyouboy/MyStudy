package cn.readsense.webviewbase.app

import android.app.Application
import cn.readsense.webviewbase.loadsir.*

import com.kingja.loadsir.core.LoadSir


/**
 *Author:qyg
 *DATE:2020/8/14 15:29
 *Description：
 **/
open class BaseApplication : Application() {

    companion object {
        public lateinit var myApplication: Application
    }

    override fun onCreate() {
        super.onCreate()
        myApplication = this@BaseApplication

        LoadSir.beginBuilder()
                .addCallback(ErrorCallback()) //添加各种状态页
                .addCallback(EmptyCallback())
                .addCallback(LoadingCallback())
                .addCallback(TimeoutCallback())
                .addCallback(CustomCallback())
                .setDefaultCallback(LoadingCallback::class.java) //设置默认状态页
                .commit()
    }
}