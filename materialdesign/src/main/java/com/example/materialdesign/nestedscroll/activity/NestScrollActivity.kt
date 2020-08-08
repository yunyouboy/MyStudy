package com.example.materialdesign.nestedscroll.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.databinding.ActivityNestScrollBinding

class NestScrollActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNestScrollBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNestScrollBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}