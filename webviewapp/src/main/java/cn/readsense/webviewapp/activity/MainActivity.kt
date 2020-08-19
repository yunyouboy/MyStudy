package cn.readsense.webviewapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cn.readsense.webviewapp.R
import cn.readsense.webviewapp.databinding.ActivityMainBinding
import cn.readsense.webviewbase.autoservice.WebViewServiceLoader
import cn.readsense.webviewcommon.autoservice.IUserCenterService
import cn.readsense.webviewcommon.autoservice.IWebViewService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main)
        binding.openWebviewactivity.setOnClickListener {
            WebViewServiceLoader.load(IWebViewService::class.java)?.run {
                //startWebViewActivity(this@MainActivity, "https://www.baidu.com", "百度", true)
                startDemoHtml(this@MainActivity)
            }
        }
    }
}
