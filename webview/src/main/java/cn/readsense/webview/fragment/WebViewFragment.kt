package cn.readsense.webview.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cn.readsense.webview.R
import cn.readsense.webview.activity.WebViewActivity
import cn.readsense.webview.databinding.FragmentWebviewBinding
import cn.readsense.webview.utils.Constants
import cn.readsense.webview.webchromclient.MyWebChromeClient
import cn.readsense.webview.webchromclient.WebChromeCallBack
import cn.readsense.webview.webviewclient.MyWebViewClient
import cn.readsense.webview.webviewclient.WebViewCallBack
import cn.readsense.webviewbase.loadsir.ErrorCallback
import cn.readsense.webviewbase.loadsir.LoadingCallback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 *Author:qyg
 *DATE:2020/8/14 10:50
 *Description：
 **/
class WebViewFragment : Fragment(), WebViewCallBack, WebChromeCallBack, OnRefreshListener {
    private val logTag = WebViewFragment::class.simpleName.toString()
    private lateinit var mBinding: FragmentWebviewBinding
    private lateinit var mLoadService: LoadService<SmartRefreshLayout>
    private lateinit var mUrl: String
    private var mCanNativeRefresh = true
    private var mIsError = false

    companion object {
        fun newInstance(url: String, canNativeRefresh: Boolean): WebViewFragment {
            val fragment = WebViewFragment()
            val bundle = Bundle()
            bundle.run {
                putString(Constants.URL, url)
                putBoolean(Constants.CAN_NATIVE_REFRESH, canNativeRefresh)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            mUrl = getString(Constants.URL) ?: ""
            mCanNativeRefresh = getBoolean(Constants.CAN_NATIVE_REFRESH, true)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)
        mBinding.webview.run {
            settings.javaScriptEnabled = true
            loadUrl(mUrl)
            webViewClient = MyWebViewClient(this@WebViewFragment)
            webChromeClient = MyWebChromeClient(this@WebViewFragment)
        }

        mBinding.smartrefreshlayout.run {
            setOnRefreshListener(this@WebViewFragment);
            setEnableRefresh(mCanNativeRefresh);
            setEnableLoadMore(false);
        }

        mLoadService = LoadSir.getDefault().register(mBinding.smartrefreshlayout) {
            mLoadService.showCallback(LoadingCallback::class.java)
            mBinding.webview.reload()
        } as LoadService<SmartRefreshLayout>

        return mLoadService.loadLayout
    }

    override fun pageStarted(url: String) {
        mLoadService?.showCallback(LoadingCallback::class.java)
    }

    override fun pageFinished(url: String) {
        if (mIsError) mBinding.smartrefreshlayout.setEnableRefresh(true) else mBinding.smartrefreshlayout.setEnableRefresh(mCanNativeRefresh)
        Log.d(logTag, "pageFinished")
        mBinding.smartrefreshlayout.finishRefresh()
        mLoadService?.run {
            if (mIsError) mLoadService.showCallback(ErrorCallback::class.java) else mLoadService.showSuccess()
        }
        mIsError = false
    }

    override fun onError() {
        Log.e(logTag, "onError")
        mIsError = true
        mBinding.smartrefreshlayout.finishRefresh()
    }

    override fun updateTitle(title: String) {
        if (activity is WebViewActivity) (activity as WebViewActivity).updateTitle(title)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mBinding.webview.reload()
    }
}