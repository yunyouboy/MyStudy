package com.example.materialdesign.coordinator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.materialdesign.R
import com.example.materialdesign.coordinator.bean.AuthorInfo

class AuthorListAdapter(private var mAuthorInfoList: MutableList<AuthorInfo>) : BaseAdapter() {

    override fun getCount(): Int {
        return if (mAuthorInfoList == null) {
            0
        } else mAuthorInfoList.size
    }

    private fun setData(authorInfoList: MutableList<AuthorInfo>) {
        mAuthorInfoList = authorInfoList
    }

    override fun getItem(position: Int): AuthorInfo {
        return mAuthorInfoList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        var viewHolder: ViewHolder? = null
        if (convertView == null) {
            val inflater = LayoutInflater.from(parent.context)
            convertView = inflater.inflate(R.layout.item_layout, parent, false)
            viewHolder = ViewHolder()
            viewHolder.mPortrait = convertView.findViewById<View?>(R.id.iv_portrait) as ImageView
            viewHolder.mNickName = convertView.findViewById<View?>(R.id.tv_nickname) as TextView
            viewHolder.mMotto = convertView.findViewById<View?>(R.id.tv_motto) as TextView
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        val authorInfo = mAuthorInfoList.get(position)
        viewHolder.mPortrait?.setImageResource(authorInfo.getPortrait())
        viewHolder.mNickName?.text = authorInfo.getNickName()
        viewHolder.mMotto?.text = authorInfo.getMotto()
        return convertView!!
    }

    fun updateItemView(listView: ListView, position: Int) {
        val index = position - listView.firstVisiblePosition
        if (index >= 0 && index < listView.childCount) {
            val authorInfo = mAuthorInfoList[position]
            authorInfo.setNickName("Google Android")
            authorInfo.setMotto("My name is Android .")
            authorInfo.setPortrait(R.mipmap.ic_launcher)
            val itemView = listView.getChildAt(index)
            getView(position, itemView, listView)
        }
    }

    private class ViewHolder {
        var mPortrait: ImageView? = null
        var mNickName: TextView? = null
        var mMotto: TextView? = null
    }
}