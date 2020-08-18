package cn.readsense.webview.mainprocess

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 *Author:qyg
 *DATE:2020/8/17 18:17
 *Description：
 **/
class MainProcessCommandService : Service() {
    override fun onBind(intent: Intent): IBinder {
        return MainProcessCommandsManager.getInstance()
    }
}