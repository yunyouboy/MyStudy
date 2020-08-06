package com.example.materialdesign.materialbutton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.databinding.ActivityMaterialButtonBinding

class MaterialButtonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMaterialButtonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaterialButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.materialbtn1.setOnClickListener {
            binding.materialbtn.isEnabled = !binding.materialbtn.isEnabled
        }
    }
}