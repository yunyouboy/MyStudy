package com.example.customizeview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_viewpage.*

/**
 *Author:qyg
 *DATE:2020/7/6 14:20
 *Description：
 **/

class ViewpagerDemoActivity : AppCompatActivity() {
    private val TAG: String = ViewpagerDemoActivity::class.java.simpleName.toString()
    private var adapter: MyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpage)
        initData()
        initView()
    }

    private fun initData() {
        adapter = MyAdapter(this, view_pager)
    }

    private fun initView() {
        view_pager.adapter = adapter
    }

    private class MyAdapter(var context: Context, parentView: ViewGroup) : PagerAdapter() {
        var viewList: ArrayList<View> = ArrayList()

        init {
            val inflater = LayoutInflater.from(context)
            for (index in 0 until 5) {
                var linearLayout = inflater.inflate(R.layout.item_viewpager, parentView, false)
                var textView = linearLayout.findViewById<TextView>(R.id.tv_item)
                textView.text = ("$index")
                viewList.add(linearLayout)
            }
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

        override fun getCount(): Int = viewList.size

        override fun instantiateItem(container: ViewGroup, position: Int): View {

            container.addView(viewList[position])
            return viewList[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(viewList[position])
        }
    }

}