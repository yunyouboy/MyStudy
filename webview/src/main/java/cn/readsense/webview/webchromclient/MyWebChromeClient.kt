package cn.readsense.webview.webchromclient

import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView

/**
 *Author:qyg
 *DATE:2020/8/14 16:51
 *Description：
 **/
class MyWebChromeClient(private var mWebChromeCallBack: WebChromeCallBack) : WebChromeClient() {
    private val tag: String = MyWebChromeClient::class.simpleName.toString()
    override fun onReceivedTitle(view: WebView?, title: String) {
        if (mWebChromeCallBack != null) mWebChromeCallBack.updateTitle(title) else Log.e(tag, "mWebChromeCallBack is null.")
    }
}