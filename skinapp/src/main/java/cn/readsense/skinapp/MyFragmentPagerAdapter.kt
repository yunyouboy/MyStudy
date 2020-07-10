package cn.readsense.skinapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author Lance
 * @date 2018/3/12
 */
class MyFragmentPagerAdapter(fragmentManager: FragmentManager, private var fragments: List<Fragment>, private var titles: List<String>) :
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }


    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

}