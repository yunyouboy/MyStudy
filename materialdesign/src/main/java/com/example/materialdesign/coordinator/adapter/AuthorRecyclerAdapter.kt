package com.example.materialdesign.coordinator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.coordinator.adapter.AuthorRecyclerAdapter.AuthorViewHolder
import com.example.materialdesign.coordinator.bean.AuthorInfo
import java.util.*

class AuthorRecyclerAdapter(private val mAuthorInfoList: MutableList<AuthorInfo>) : RecyclerView.Adapter<AuthorViewHolder>() {

    /**
     * item view 的类型是否是小类型的
     */
    private var mIsSmall = false
    fun setSmallType(isSmall: Boolean) {
        mIsSmall = isSmall
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val inflater = LayoutInflater.from(parent.getContext())
        var childView: View? = null
        childView = inflater.inflate(R.layout.item_layout, parent, false)
        return AuthorViewHolder(childView)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val authorInfo = mAuthorInfoList[position]
        holder.mPortraitView.setImageResource(authorInfo.getPortrait())
        holder.mNickNameView.text = authorInfo.getNickName()
        holder.mMottoView.text = authorInfo.getMotto()
    }

    override fun getItemCount(): Int {
        return mAuthorInfoList?.size ?: 0
    }

    /**
     * 移动Item
     *
     * @param fromPosition
     * @param toPosition
     */
    fun moveItem(fromPosition: Int, toPosition: Int) {
        //做数据的交换
        if (fromPosition < toPosition) {
            for (index in fromPosition until toPosition) {
                Collections.swap(mAuthorInfoList, index, index + 1)
            }
        } else {
            for (index in fromPosition downTo toPosition + 1) {
                Collections.swap(mAuthorInfoList, index, index - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    /**
     * 滑动Item
     *
     * @param position
     */
    fun removeItem(position: Int) {
        mAuthorInfoList.removeAt(position) //删除数据
        notifyItemRemoved(position)
    }

    inner class AuthorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mPortraitView: ImageView = itemView.findViewById<View?>(R.id.iv_portrait) as ImageView
        var mNickNameView: TextView = itemView.findViewById<View?>(R.id.tv_nickname) as TextView
        var mMottoView: TextView = itemView.findViewById<View?>(R.id.tv_motto) as TextView

    }

}