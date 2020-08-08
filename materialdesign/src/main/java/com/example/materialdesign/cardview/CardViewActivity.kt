package com.example.materialdesign.cardview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityCardViewBinding
import com.example.materialdesign.databinding.ItemLayoutBinding

class CardViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardViewBinding
    private lateinit var itemLayoutBinding: ItemLayoutBinding
    private lateinit var itemLayoutBinding1: ItemLayoutBinding
    private lateinit var itemLayoutBinding2: ItemLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardViewBinding.inflate(layoutInflater)
        itemLayoutBinding = ItemLayoutBinding.inflate(layoutInflater, binding.cardview, true)
        itemLayoutBinding1 = ItemLayoutBinding.inflate(layoutInflater, binding.cardview1, true)
        itemLayoutBinding2 = ItemLayoutBinding.inflate(layoutInflater, binding.cardview2, true)
        setContentView(binding.root)
        itemLayoutBinding.ivPortrait.setImageDrawable(getDrawable(R.drawable.xiaoxin))
        itemLayoutBinding.tvMotto.text = "Hi,美女，喜欢吃青椒吗？"
        itemLayoutBinding.tvNickname.text = "蜡笔小新"
        itemLayoutBinding1.ivPortrait.setImageDrawable(getDrawable(R.drawable.mingren))
        itemLayoutBinding1.tvMotto.text = "我是要成为火影的男人！！！"
        itemLayoutBinding1.tvNickname.text = "鸣人"
        itemLayoutBinding2.ivPortrait.setImageDrawable(getDrawable(R.drawable.liudao))
        itemLayoutBinding2.tvMotto.text = "触碰万物之理，能控制森罗万象"
        itemLayoutBinding2.tvNickname.text = "六道仙人"
    }
}