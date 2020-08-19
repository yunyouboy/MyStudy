package cn.readsense.webviewapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cn.readsense.webviewapp.R
import cn.readsense.webviewapp.databinding.ActivityMainProcessTest2Binding
import cn.readsense.webviewapp.databinding.ActivityMainProcessTestBinding

/**
 *Author:qyg
 *DATE:2020/8/18 10:09
 *Description：
 **/
class MainProcessTest2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMainProcessTest2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainProcessTest2Activity, R.layout.activity_main_process_test2)
    }
}