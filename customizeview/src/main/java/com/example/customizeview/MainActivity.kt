package com.example.customizeview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_to_viewpager.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, ViewpagerDemoActivity::class.java)
            startActivity(intent)
        }

        tv_to_center_text.setOnClickListener {
            val intent = Intent(this@MainActivity, CenterTextActivity::class.java)
            startActivity(intent)
        }
    }
}
