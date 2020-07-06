package cn.readsense.kotlinactualcombat.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import cn.readsense.kotlinactualcombat.R
import cn.readsense.kotlinactualcombat.entity.HomeDataResponse

/**
 *Author:qyg
 *DATE:2020/7/1 11:06
 *Description：
 **/
class HomeInfoListAdapter private constructor() : BaseAdapter() {
    // 数据
    private lateinit var mNews: List<HomeDataResponse.NewsListBean>
    private lateinit var context: Context

    constructor(context: Context, news: List<HomeDataResponse.NewsListBean>) : this() {
        this.context = context
        this.mNews = news
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var viewHolder: ViewHolder

        var view: View? = null

        if (convertView == null) {
            view = View.inflate(this.context, R.layout.home_list_item, null)

            viewHolder = ViewHolder
            viewHolder.newsText = view.findViewById(R.id.news_text);
            viewHolder.newDate = view.findViewById(R.id.news_date);

            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // 设置条目数据
        val news: HomeDataResponse.NewsListBean = mNews.get(position)
        viewHolder.newsText.setText(news.title)
        viewHolder.newDate.setText(news.create_time)
        return view
    }

    override fun getItem(position: Int): Any = mNews[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int {
        return mNews.size
    }

    object ViewHolder {
        lateinit var newsText: TextView // 标题
        lateinit var newDate: TextView // 时间
    }
}