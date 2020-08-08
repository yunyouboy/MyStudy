package com.example.materialdesign.translucent

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.databinding.ActivityQqMusicBinding

/**
 * 1. 在values、values-v19、values-v21的style.xml都设置一个 Translucent System Bar 风格的Theme
 * 2. 在AndroidManifest.xml中对指定Activity的theme进行设置
 * 3. android:fitsSystemWindows设置为true
 */
class QQMusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQqMusicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        binding = ActivityQqMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}