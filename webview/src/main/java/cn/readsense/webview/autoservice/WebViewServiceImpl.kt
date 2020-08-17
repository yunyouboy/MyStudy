package cn.readsense.webview.autoservice

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import cn.readsense.webview.activity.WebViewActivity
import cn.readsense.webview.fragment.WebViewFragment
import cn.readsense.webview.utils.Constants
import cn.readsense.webviewcommon.autoservice.IWebViewService
import com.google.auto.service.AutoService

/**
 *Author:qyg
 *DATE:2020/8/13 15:33
 *Description：
 **/
@AutoService(IWebViewService::class)
class WebViewService : IWebViewService {
    override fun startWebViewActivity(context: Context, url: String, title: String, isShowActionBar: Boolean) {
        val intent = Intent(context, WebViewActivity::class.java).run {
            putExtra(Constants.TITLE, title)
            putExtra(Constants.URL, url)
            putExtra(Constants.IS_SHOW_ACTION_BAR, isShowActionBar)
        }
        context.startActivity(intent)
    }

    override fun getWebViewFragment(url: String, canNativeRefresh: Boolean): Fragment {
        return WebViewFragment.newInstance(url, canNativeRefresh)
    }

    override fun startDemoHtml(context: Context) {
        val intent = Intent(context, WebViewActivity::class.java).run {
            putExtra(Constants.TITLE, "本地Demo测试页")
            putExtra(Constants.URL, Constants.ANDROID_ASSET_URI.toString() + "demo.html")
        }
        context.startActivity(intent)
    }
}