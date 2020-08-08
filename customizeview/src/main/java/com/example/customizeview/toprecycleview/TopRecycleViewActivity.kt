package com.example.customizeview.toprecycleview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customizeview.R
import com.example.customizeview.databinding.RecycleViewBinding
import com.example.customizeview.slidecardview.CardConfig

class TopRecycleViewActivity : AppCompatActivity() {

    private lateinit var binding: RecycleViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@TopRecycleViewActivity, R.layout.activity_top_recycleview)

        // 初始化数据
        CardConfig.initConfig(this)

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(this@TopRecycleViewActivity)
            addItemDecoration(StarDecoration())
            adapter = StarAdapter(getList()) {}
        }
    }

    private fun getList(): MutableList<Star> {
        var starList = ArrayList<Star>()
        for (i in 0..3) {
            for (j in 0..19) {
                if (i % 2 == 0) {
                    starList.add(Star("何X$j", "快乐家族$i"))
                } else {
                    starList.add(Star("汪涵$j", "天天兄弟$i"))
                }
            }
        }
        return starList
    }
}