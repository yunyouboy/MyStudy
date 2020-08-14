package cn.readsense.webviewcommon.autoservice

import android.content.Context
import androidx.fragment.app.Fragment

/**
 *Author:qyg
 *DATE:2020/8/13 15:21
 *Description：
 **/
interface IWebViewService {
    fun startWebViewActivity(context: Context, url: String, title: String, isShowActionBar: Boolean)
    fun getWebViewFragment(url: String, canNativeRefresh: Boolean): Fragment
}