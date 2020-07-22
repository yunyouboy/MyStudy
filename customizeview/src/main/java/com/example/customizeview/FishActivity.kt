package com.example.customizeview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customizeview.view.FishDrawable
import kotlinx.android.synthetic.main.activity_fish.*

class FishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish)

        //iv_fish.setImageDrawable(FishDrawable())
    }
}