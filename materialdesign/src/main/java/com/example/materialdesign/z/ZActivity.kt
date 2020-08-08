package com.example.materialdesign.z

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R

/**
 * 在android的世界里面，elevation起到了权重的作用，而且，每一个View的默认权重都是0dp，但在Google说他是一个阴影的作用，但是实际上由于MD设计的高度Z造成的
 */
class ZActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_z)
    }
}