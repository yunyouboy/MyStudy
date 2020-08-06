package com.example.materialdesign.floatingactionbutton

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.databinding.ActivityFloatActionButtonBinding
import com.google.android.material.snackbar.Snackbar

class FloatActionButtonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFloatActionButtonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFloatActionButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Hello floatingactionbutton...", Snackbar.LENGTH_LONG)
                    .setAction("取消") { Toast.makeText(this@FloatActionButtonActivity, "取消", Toast.LENGTH_SHORT).show() }.show()
        }
    }
}