package cn.readsense.lazyloadviewpage.lazyload.vp2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import cn.readsense.lazyloadviewpage.R
import cn.readsense.lazyloadviewpage.lazyload.FragmentDelegater
import cn.readsense.lazyloadviewpage.lazyload.base.LazyFragment5
import java.util.*

/**
 * 同学们，Fragment2 T2 里面的 Fragment 【的确一点绕，需要同学们理解下】
 */
class MyFragment2 : LazyFragment5() {
    private var viewPager: ViewPager? = null//对应的viewPager
    private var fragmentsList: ArrayList<Fragment>? = null//view数组

    protected override val layoutRes: Int
        get() = R.layout.fragment_2_vp

    override fun initView(rootView: View) {
        viewPager = rootView.findViewById(R.id.viewpager01)
        fragmentsList = ArrayList<Fragment>()

        // 又加载四个 子Fragment
        fragmentsList!!.add(Fragment2_vp_1.newIntance())
        fragmentsList!!.add(Fragment2_vp_2.newIntance())
        fragmentsList!!.add(Fragment2_vp_3.newIntance())
        fragmentsList!!.add(Fragment2_vp_4.newIntance())
        /**
         * 实例化一个PagerAdapter
         * 必须重写的两个方法
         * getCount
         * getItem
         */
        val pagerAdapter: PagerAdapter = object : FragmentPagerAdapter(getChildFragmentManager()) {
            override fun getItem(i: Int): Fragment = fragmentsList!![i]


            override fun getCount(): Int = fragmentsList!!.size
        }
        viewPager?.adapter = pagerAdapter
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: " + "Fragment2")
    }

    override fun onPause() {
        super.onPause()
    }

    companion object {
        private const val TAG = "Fragment2"
        fun newIntance(): Fragment {
            val fragment = MyFragment2()
            fragment.setFragmentDelegater(FragmentDelegater(fragment))
            return fragment
        }
    }
}