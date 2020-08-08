package com.example.customizeview.slidecardview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.customizeview.R
import com.example.customizeview.databinding.SlideCardViewBinding

class SlideCardActivity : AppCompatActivity() {
    private lateinit var binding: SlideCardViewBinding
    private lateinit var data: MutableList<SlideCardBean>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SlideCardActivity, R.layout.activity_slide_card)
        initData()
        initView()
        initEvent()
    }

    private fun initData() {
        // 初始化数据
        CardConfig.initConfig(applicationContext)
        data = SlideCardBean.initDatas()
    }

    private fun initView() {
        binding.rv.apply {
            layoutManager = SlideCardLayoutManager()
            adapter = SlideCardAdapter(data) {}
        }
    }

    private fun initEvent() {
        val slideCallback = SlideCardCallback(binding.rv, data)
        val itemHelper = ItemTouchHelper(slideCallback)
        itemHelper.attachToRecyclerView(binding.rv)
    }

}