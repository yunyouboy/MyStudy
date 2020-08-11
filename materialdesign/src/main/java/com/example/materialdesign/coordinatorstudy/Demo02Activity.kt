package com.example.materialdesign.coordinatorstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.materialdesign.R
import com.example.materialdesign.comm.CommonRecyclerAdapter
import com.example.materialdesign.comm.CommonRecyclerHolder
import com.example.materialdesign.databinding.ActivityDemo02Binding
import java.util.*

class Demo02Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDemo02Binding
    private val mText = "自定义Behavior"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDemo02Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.run {
            recyclerView.layoutManager = LinearLayoutManager(this@Demo02Activity)
            recyclerView.adapter = object : CommonRecyclerAdapter<String>(this@Demo02Activity, createData(), R.layout.item_recycle) {
                override fun convert(holder: CommonRecyclerHolder, item: String, position: Int, isScrolling: Boolean) {
                    holder.setText(R.id.item_tv, item)
                }
            }
            recyclerView.addItemDecoration(DividerItemDecoration(this@Demo02Activity, DividerItemDecoration.VERTICAL))
        }
    }

    private fun createData(): MutableList<String> {
        val result = ArrayList<String>(100)
        for (i in 0..99) {
            result.add(mText + i)
        }
        return result
    }
}