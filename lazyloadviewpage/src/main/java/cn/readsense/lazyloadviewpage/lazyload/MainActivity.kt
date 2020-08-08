package cn.readsense.lazyloadviewpage.lazyload

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import cn.readsense.lazyloadviewpage.R
import cn.readsense.lazyloadviewpage.lazyload.Fragment5.Companion.instance
import cn.readsense.lazyloadviewpage.lazyload.vp2.MyFragment2
import com.google.android.material.bottomnavigation.BottomNavigationView

// ViewPager + Fragment 【使用篇】 == 工作过一年 都知道
// 同学们：关于ViewPager + Fragment 的基本使用，这里就简单过了下了，希望同学们能理解哈
class MainActivity : AppCompatActivity() {
    var mViewPager: ViewPager? = null
    private var bottomNavigationView: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView!!.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val fragmentList: ArrayList<Fragment> = ArrayList<Fragment>()
        fragmentList.apply {
            add(MyFragment.newInstance(1))
            //add(MyFragment.newInstance(2));
            add(MyFragment2.newIntance()) // 第五版的改动点
            add(MyFragment.newInstance(3))
            add(MyFragment.newInstance(4))
            add(instance())
        }

        val myAdapter = MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList)
        mViewPager?.run {
            adapter = myAdapter!!
            offscreenPageLimit = 2
            setOnPageChangeListener(viewpagerChangeListener)
        }
    }

    // tab 名称设置，例如： T1, T2, T3, T4, T5
    private var viewpagerChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
        override fun onPageSelected(i: Int) {
            var itemId: Int = R.id.fragment_1
            when (i) {
                0 -> itemId = R.id.fragment_1
                1 -> itemId = R.id.fragment_2
                2 -> itemId = R.id.fragment_3
                3 -> itemId = R.id.fragment_4
                4 -> itemId = R.id.fragment_5
            }
            bottomNavigationView?.selectedItemId = itemId
        }

        override fun onPageScrollStateChanged(i: Int) {}
    }

    // 当点击 tab1 的时候 就会 设置CurrentItem=0，来设置 ViewPager下标操作
    private var onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.fragment_1 -> {
                    mViewPager?.setCurrentItem(0, true)
                    return true
                }
                R.id.fragment_2 -> {
                    mViewPager?.setCurrentItem(1, true)
                    return true
                }
                R.id.fragment_3 -> {
                    mViewPager?.setCurrentItem(2, true)
                    return true
                }
                R.id.fragment_4 -> {
                    mViewPager?.setCurrentItem(3, true)
                    return true
                }
                R.id.fragment_5 -> {
                    mViewPager?.setCurrentItem(4, true)
                    return true
                }
            }
            return false
        }
    }
}