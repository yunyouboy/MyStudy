package com.example.customizeview.slidecardview2

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.bumptech.glide.Glide
import com.example.customizeview.R
import com.example.customizeview.databinding.SlideCardViewBinding
import com.example.customizeview.slidecardview.CardConfig
import com.example.customizeview.slidecardview2.adapter.UniversalAdapter
import com.example.customizeview.slidecardview2.adapter.ViewHolder

class SlideCardActivity : AppCompatActivity() {
    private lateinit var binding: SlideCardViewBinding
    private lateinit var data: ArrayList<SlideCardBean>
    private lateinit var mAdapter: UniversalAdapter<SlideCardBean>

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
        mAdapter = object : UniversalAdapter<SlideCardBean>(this, data, R.layout.item_swipe_card) {
            override fun convert(viewHolder: ViewHolder, slideCardBean: SlideCardBean) {
                viewHolder.setText(R.id.tvName, slideCardBean.name)
                viewHolder.setText(R.id.tvPrecent, slideCardBean.position.toString() + "/" + mDatas.size)
                Glide.with(this@SlideCardActivity)
                        .load(slideCardBean.url)
                        .into(viewHolder.getView(R.id.iv) as ImageView)
            }
        }
    }

    private fun initView() {
        binding.rv.apply {
            layoutManager = SlideCardLayoutManager()
            adapter = mAdapter
        }
    }

    private fun initEvent() {
        val slideCallback = SlideCardCallback(binding.rv, data)
        val itemHelper = ItemTouchHelper(slideCallback)
        itemHelper.attachToRecyclerView(binding.rv)
    }

}