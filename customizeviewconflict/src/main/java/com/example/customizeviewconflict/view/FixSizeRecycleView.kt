package com.example.customizeviewconflict.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customizeviewconflict.R
import com.example.customizeviewconflict.entity.TitleBean

class FixSizeRecycleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.recyclerViewStyle) : RecyclerView(context, attrs, defStyleAttr) {

    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adapter = RecyclerAdapter(arrayListOf<TitleBean>(TitleBean("qyg1"), TitleBean("qyg2"), TitleBean("qyg3"), TitleBean("qyg4"))) {
            Toast.makeText(context, "FixSizeRecycleView click on position $it", Toast.LENGTH_LONG).show()
        }
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        return false
    }
}