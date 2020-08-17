package cn.readsense.webview.webviewprocess.settings

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import cn.readsense.webview.BuildConfig

class WebViewDefaultSettings private constructor() {
    private lateinit var mWebSettings: WebSettings

    companion object {
        val instance: WebViewDefaultSettings
            get() = WebViewDefaultSettings()
    }

    fun setSettings(webView: WebView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) WebView.enableSlowWholeDocumentDraw()
        mWebSettings = webView.settings
        mWebSettings.run {
            javaScriptEnabled = true
            setSupportZoom(true)
            builtInZoomControls = false
            cacheMode = if (isNetworkConnected(webView.context)) WebSettings.LOAD_DEFAULT else WebSettings.LOAD_CACHE_ELSE_NETWORK
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            // 硬件加速兼容性问题有点多
            /*when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                    webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                    webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                }
                Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT -> {
                    webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                }
            }*/
            textZoom = 100
            databaseEnabled = true
            setAppCacheEnabled(true)
            loadsImagesAutomatically = true
            setSupportMultipleWindows(false)
            blockNetworkImage = false //是否阻塞加载网络图片  协议http or https
            allowFileAccess = true //允许加载本地文件html  file协议
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                allowFileAccessFromFileURLs = false //通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
                allowUniversalAccessFromFileURLs = false //允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
            }
            mWebSettings.javaScriptCanOpenWindowsAutomatically = true
            layoutAlgorithm = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) WebSettings.LayoutAlgorithm.SINGLE_COLUMN else WebSettings.LayoutAlgorithm.NORMAL
            savePassword = false
            saveFormData = false
            loadWithOverviewMode = true
            useWideViewPort = true
            domStorageEnabled = true
            setNeedInitialFocus(true)
            defaultTextEncodingName = "utf-8" //设置编码格式
            defaultFontSize = 16
            minimumFontSize = 10 //设置 WebView 支持的最小字体大小，默认为 8
            setGeolocationEnabled(true)
            useWideViewPort = true
            val appCacheDir = webView.context.getDir("cache", Context.MODE_PRIVATE).path
            Log.i("WebSetting", "WebView cache dir: $appCacheDir")
            databasePath = appCacheDir
            setAppCachePath(appCacheDir)
            setAppCacheMaxSize(1024 * 1024 * 80.toLong())

            // 用户可以自己设置useragent
            // mWebSettings.setUserAgentString("webprogress/build you agent info");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
            }
        }
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }
}