package com.example.customizeview.slidecardview

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class CustomerBinding {
    companion object {
        @BindingAdapter("app:imgUrl")
        @JvmStatic
        fun load(view: ImageView, url: String) {
            Glide.with(view.context).load(url).into(view)
        }

        @BindingAdapter("app:int2String")
        @JvmStatic
        fun int2String(view: TextView, int: Int) {
            view.text = int.toString()
        }

    }
}