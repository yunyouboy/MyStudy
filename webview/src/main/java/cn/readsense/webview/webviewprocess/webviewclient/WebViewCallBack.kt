package cn.readsense.webview.webviewprocess.webviewclient

/**
 *Author:qyg
 *DATE:2020/8/14 16:13
 *Description：
 **/
interface WebViewCallBack {
    fun pageStarted(url: String)
    fun pageFinished(url: String)
    fun onError()
}