package com.example.customizeview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customizeview.slidecardview2.SlideCardActivity
import com.example.customizeview.toprecycleview.TopRecycleViewActivity
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

        tv_to_fish.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, FishActivity::class.java)
            startActivity(intent)
        }

        tv_to_recycle_view.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, TopRecycleViewActivity::class.java)
            startActivity(intent)
        }

        tv_to_slide_card_view.setOnClickListener{
            val intent: Intent = Intent(this@MainActivity, SlideCardActivity::class.java)
            startActivity(intent)
        }
    }
}
