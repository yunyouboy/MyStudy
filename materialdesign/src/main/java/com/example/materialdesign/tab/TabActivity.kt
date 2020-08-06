package com.example.materialdesign.tab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.databinding.ActivityTabBinding

class TabActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTabBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}