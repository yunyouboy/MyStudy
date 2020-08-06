package com.example.materialdesign.viewpager2

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.materialdesign.databinding.ActivityViewPager2Binding
import com.example.materialdesign.viewpager2.adapter.MyFragmentAdapter
import com.example.materialdesign.viewpager2.fragment.FourFragment
import com.example.materialdesign.viewpager2.fragment.OneFragment
import com.example.materialdesign.viewpager2.fragment.ThreeFragment
import com.example.materialdesign.viewpager2.fragment.TwoFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class ViewPager2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPager2Binding
    private lateinit var fragmentList: MutableList<Fragment>
    private lateinit var titles: MutableList<String?>
    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        //        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapsingToolbarLayout.title = "ViewPager2"
        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                binding.collapsingToolbarLayout.title = "ViewPager2"
            } else {
                binding.collapsingToolbarLayout.title = ""
            }
        })
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        fragmentList = ArrayList()
        fragmentList.add(OneFragment.Companion.newIntance())
        fragmentList.add(TwoFragment.Companion.newIntance())
        fragmentList.add(ThreeFragment.Companion.newIntance())
        fragmentList.add(FourFragment.Companion.newIntance())
        titles = ArrayList()
        titles.add("OneFragment")
        titles.add("TwoFragment")
        titles.add("ThreeFragment")
        titles.add("FourFragment")
        val myFragmentAdapter = MyFragmentAdapter(this, fragmentList)
        binding.viewPager.adapter = myFragmentAdapter
        TabLayoutMediator(binding.tab, binding.viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position -> tab.text = titles.get(position) }).attach()
    }
}