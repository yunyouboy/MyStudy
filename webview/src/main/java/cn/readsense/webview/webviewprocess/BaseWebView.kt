package cn.readsense.webview.webviewprocess

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import cn.readsense.webview.bean.JsParam
import cn.readsense.webview.webviewprocess.settings.WebViewDefaultSettings
import cn.readsense.webview.webviewprocess.webchromclient.MyWebChromeClient
import cn.readsense.webview.webviewprocess.webchromclient.WebChromeCallBack
import cn.readsense.webview.webviewprocess.webviewclient.MyWebViewClient
import cn.readsense.webview.webviewprocess.webviewclient.WebViewCallBack
import com.google.gson.Gson

/**
 *Author:qyg
 *DATE:2020/8/17 13:48
 *Description：
 **/
class BaseWebView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 301231) : WebView(context, attrs, defStyle) {
    private val tag: String = BaseWebView::class.simpleName.toString()

    init {
        WebViewProcessCommandDispatcher.getInstance().initAidlConnection()
        WebViewDefaultSettings.instance.setSettings(this@BaseWebView)
        addJavascriptInterface(this@BaseWebView, "mywebview")
    }

    fun registerWebViewCallBack(webViewCallBack: WebViewCallBack?, webChromeCallBack: WebChromeCallBack?) {
        if (null != webViewCallBack) webViewClient = MyWebViewClient(webViewCallBack)
        if (null != webChromeCallBack) webChromeClient = MyWebChromeClient(webChromeCallBack)
    }

    @JavascriptInterface
    fun takeNativeAction(jsParam: String) {
        Log.i(tag, jsParam)
        jsParam?.run {
            val jsParamObject: JsParam = Gson().fromJson(jsParam, JsParam::class.java)
            jsParamObject?.run {
                WebViewProcessCommandDispatcher.getInstance().executeCommand(jsParamObject.name, Gson().toJson(jsParamObject.param), this@BaseWebView)
            }
        }
    }

    fun handleCallback(callbackName: String, response: String) {
        if (!TextUtils.isEmpty(callbackName) && !TextUtils.isEmpty(response)) {
            post {
                val jscode = "javascript:xiangxuejs.callback('$callbackName',$response)"
                Log.e("xxxxxx", jscode)
                evaluateJavascript(jscode, null)
            }
        }
    }
}