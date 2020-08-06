package com.example.materialdesign.chips

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.text.Spanned
import android.text.style.ImageSpan
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.chips.ChipsActivity
import com.example.materialdesign.databinding.ActivityChipsBinding
import com.google.android.material.chip.ChipDrawable

class ChipsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChipsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.chip0.setOnClickListener {
            val chipDrawable = ChipDrawable.createFromResource(this@ChipsActivity, R.xml.chip_test)
            chipDrawable.setBounds(0, 0, chipDrawable.intrinsicWidth, chipDrawable.intrinsicHeight)
            val span = ImageSpan(chipDrawable)
            val text = binding.etTest.text
            chipDrawable.text = text.toString()
            text.setSpan(span, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        binding.seekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val max = seekBar.max
                val scale = progress as Double / max as Double
                val drawable = binding.ivShow.background as ClipDrawable
                drawable.level = (10000 * scale) as Int
                binding.tvInfo.text = progress.toString() + ""
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        binding.materialbtn1.setOnClickListener {
            binding.materialbtn.isEnabled = !binding.materialbtn.isEnabled
        }
    }
}