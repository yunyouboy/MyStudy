package com.example.customizeview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.customizeview.view.ColorChangeTextView
import kotlinx.android.synthetic.main.activity_viewpage.*

/**
 *Author:qyg
 *DATE:2020/7/6 14:20
 *Description：
 **/

class ViewpagerDemoActivity : AppCompatActivity() {
    private val TAG: String = ViewpagerDemoActivity::class.java.simpleName.toString()
    private var adapter: MyAdapter? = null
    var mTabs: MutableList<ColorChangeTextView> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpage)
        initData()
        initView()
        initEvent()
    }

    private fun initEvent() {
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (position >= mTabs.size - 1) {
                    return
                }
                val left: ColorChangeTextView = mTabs[position]
                val right: ColorChangeTextView = mTabs[position + 1]

                left.mDirection = ColorChangeTextView.direction_right
                right.mDirection = ColorChangeTextView.direction_left
                left.mProgress = 1 - positionOffset
                right.mProgress = positionOffset
            }

            override fun onPageSelected(position: Int) {

            }

        })
    }

    private fun initData() {

    }

    private fun initView() {
        mTabs.apply {
            add(id_tab_01)
            add(id_tab_02)
            add(id_tab_03)
            add(id_tab_04)
        }
        adapter = MyAdapter(this, mTabs.size, view_pager)
        view_pager.adapter = adapter
    }

    private class MyAdapter(var context: Context, size: Int, parentView: ViewGroup) : PagerAdapter() {
        var viewList: ArrayList<View> = ArrayList()

        init {
            val inflater = LayoutInflater.from(context)
            for (index in 0 until size) {
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