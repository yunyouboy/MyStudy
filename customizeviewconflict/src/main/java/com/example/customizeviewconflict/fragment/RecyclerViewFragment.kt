package com.example.customizeviewconflict.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customizeviewconflict.R
import com.example.customizeviewconflict.entity.TitleBean
import com.example.customizeviewconflict.view.RecyclerAdapter
import java.util.*

class RecyclerViewFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_recycler_view, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = RecyclerAdapter(getData()) {

        }
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private val THRESHOLD_LOAD_MORE = 3
            private var hasLoadMore = false
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    hasLoadMore = false
                }
                if (newState != RecyclerView.SCROLL_STATE_DRAGGING && !hasLoadMore) {
                    val lastPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    val offset = recyclerView.adapter!!.itemCount - lastPosition - 1
                    if (offset <= THRESHOLD_LOAD_MORE) {
                        hasLoadMore = true
                        adapter.addDatas(getData())
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
        return view
    }

    private var i = 0

    private fun getData(): MutableList<TitleBean> {
        val data: MutableList<TitleBean> = ArrayList()
        val tempI = i
        while (i < tempI + 10) {
            data.add(TitleBean("ChildView item $i"))
            i++
        }
        return data
    }
}