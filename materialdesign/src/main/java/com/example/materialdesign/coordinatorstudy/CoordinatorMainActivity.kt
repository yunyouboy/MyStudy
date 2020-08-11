package com.example.materialdesign.coordinatorstudy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.databinding.ActivityCoordinatorMainBinding

class CoordinatorMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoordinatorMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinatorMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDemo1.setOnClickListener { startActivity(Intent(this@CoordinatorMainActivity, Demo01Activity::class.java)) }
        binding.btnDemo2.setOnClickListener { startActivity(Intent(this@CoordinatorMainActivity, Demo02Activity::class.java)) }
    }
}