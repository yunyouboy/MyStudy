package cn.readsense.webview.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cn.readsense.webview.R
import cn.readsense.webview.databinding.ActivityWebviewBinding
import cn.readsense.webview.fragment.WebViewFragment
import cn.readsense.webview.utils.Constants

class WebViewActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityWebviewBinding>(this@WebViewActivity, R.layout.activity_webview)
        mBinding.run {
            title.text = intent.getStringExtra(Constants.TITLE)
            supportFragmentManager.beginTransaction().replace(R.id.web_view_fragment, WebViewFragment.newInstance(intent.getStringExtra(Constants.URL), true)).commit()
            actionBar.visibility = if (intent.getBooleanExtra(Constants.IS_SHOW_ACTION_BAR, true)) View.VISIBLE else View.GONE
            back.setOnClickListener { finish() }
        }
    }

    internal fun updateTitle(title: String) {
        mBinding.title.text = title
    }

}