package com.example.materialdesign.bottomAppBar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.databinding.ActivityBottomAppBarBinding

class BottomAppBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomAppBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomAppBarBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
    }
}