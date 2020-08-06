package com.example.materialdesign.coordinator

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.materialdesign.coordinator.adapter.AuthorRecyclerAdapter
import com.example.materialdesign.coordinator.bean.AuthorInfo
import com.example.materialdesign.databinding.ActivityCoordinatorBinding

class CoordinatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoordinatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        //        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinatorBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        binding.collapsingToolbarLayout.title = "蜡笔小新的梦想"
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = AuthorRecyclerAdapter(AuthorInfo.Companion.createTestData())
    }
}