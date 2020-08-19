package cn.readsense.webviewapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cn.readsense.webviewapp.R
import cn.readsense.webviewapp.databinding.ActivityMainProcessTestBinding

/**
 *Author:qyg
 *DATE:2020/8/18 10:09
 *Description：
 **/
class MainProcessTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainProcessTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainProcessTestActivity, R.layout.activity_main_process_test)
    }
}