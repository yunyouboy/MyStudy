package cn.readsense.webview.webviewclient

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 *Author:qyg
 *DATE:2020/8/14 15:59
 *Description：
 **/
class MyWebViewClient(private var mWebViewCallBack: WebViewCallBack) : WebViewClient() {
    private val tag = MyWebViewClient::class.simpleName.toString()

    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        if (null != mWebViewCallBack) mWebViewCallBack.pageStarted(url) else Log.e(tag, "WebViewCallBack is null.")
    }

    override fun onPageFinished(view: WebView, url: String) {
        if (null != mWebViewCallBack) mWebViewCallBack.pageFinished(url) else Log.e(tag, "WebViewCallBack is null.")
    }

    override fun onReceivedError(view: WebView, request: WebResourceRequest?, error: WebResourceError?) {
        if (null != mWebViewCallBack) mWebViewCallBack.onError() else Log.e(tag, "WebViewCallBack is null.")
    }
}