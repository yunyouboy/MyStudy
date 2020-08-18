package cn.readsense.webview.webviewprocess

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import cn.readsense.webview.IWebViewProcessToMainProcessInterface
import cn.readsense.webview.mainprocess.MainProcessCommandService
import cn.readsense.webviewbase.app.BaseApplication

/**
 *Author:qyg
 *DATE:2020/8/17 18:00
 *Description：
 **/
class WebViewProcessCommandDispatcher : ServiceConnection {
    companion object {
        private var sInstance: WebViewProcessCommandDispatcher? = null
        fun getInstance(): WebViewProcessCommandDispatcher {
            if (null == sInstance) {
                synchronized(WebViewProcessCommandDispatcher::class) {
                    sInstance = WebViewProcessCommandDispatcher()
                }
            }
            return sInstance!!
        }
    }

    private var iWebViewProcessToMainProcessInterface: IWebViewProcessToMainProcessInterface? = null

    override fun onServiceConnected(name: ComponentName, service: IBinder) {
        iWebViewProcessToMainProcessInterface = IWebViewProcessToMainProcessInterface.Stub.asInterface(service)
    }

    override fun onServiceDisconnected(name: ComponentName) {
        iWebViewProcessToMainProcessInterface = null
        initAidlConnection()
    }


    override fun onBindingDied(name: ComponentName) {
        iWebViewProcessToMainProcessInterface = null
        initAidlConnection()
    }

    fun initAidlConnection() {
        val intent = Intent(BaseApplication.myApplication, MainProcessCommandService::class.java)
        BaseApplication.myApplication.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    fun executeCommand(commandName: String, params: String, baseWebView: BaseWebView) {
        iWebViewProcessToMainProcessInterface?.handleWebCommand(commandName, params)
    }
}