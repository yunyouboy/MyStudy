package com.example.customizeviewconflict.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(var fragmentList: MutableList<Fragment>, fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}