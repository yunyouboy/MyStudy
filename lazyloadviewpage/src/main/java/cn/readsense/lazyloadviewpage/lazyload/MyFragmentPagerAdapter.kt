package cn.readsense.lazyloadviewpage.lazyload

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * 同学们大家好：专门用来显示 ViewPager Item 的适配器
 */
class MyFragmentPagerAdapter(fm: FragmentManager, list: List<Fragment>) : FragmentPagerAdapter(fm) {
    private val fragmentList: List<Fragment> = list
    override fun getItem(i: Int): Fragment {
        return fragmentList[i]
    }

    override fun getCount(): Int = fragmentList.size

    companion object {
        private const val TAG = "MyFragmentPagerAdapter"
    }

}