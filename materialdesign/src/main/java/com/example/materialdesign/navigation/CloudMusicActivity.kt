package com.example.materialdesign.navigation

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.materialdesign.databinding.ActivityCloudMusicBinding

class CloudMusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCloudMusicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        binding = ActivityCloudMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.llMsg.setOnClickListener { binding.drawerLayout.closeDrawers() }
        binding.navView.setNavigationItemSelectedListener {
            binding.drawerLayout.closeDrawer(GravityCompat.END)
            true
        }
    }
}