package cn.readsense.webview.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
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
                if ("showToast".equals(jsParamObject.name, ignoreCase = true))
                    Toast.makeText(context, (Gson().fromJson(jsParamObject.param, MutableMap::class.java)["message"]).toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}