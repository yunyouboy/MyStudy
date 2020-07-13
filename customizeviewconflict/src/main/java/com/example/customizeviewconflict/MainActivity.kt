package com.example.customizeviewconflict

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.customizeviewconflict.databinding.ActivityMainBinding
import com.example.customizeviewconflict.fragment.RecyclerViewFragment
import com.example.customizeviewconflict.viewpager.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewpage.adapter = ViewPagerAdapter(getPageFragments(), this@MainActivity)

        val labels = arrayOf("linear", "scroll", "recycler")
        TabLayoutMediator(binding.tablayout, binding.viewpage, TabConfigurationStrategy { tab, position ->
            tab.text = labels[position]
        }).attach()
        binding.swipeRefreshLayout?.setOnRefreshListener {
            binding.root.postDelayed(
                    { binding.swipeRefreshLayout?.isRefreshing = false },
                    1000
            )
        }
    }

    private fun getPageFragments(): MutableList<Fragment> = arrayListOf(RecyclerViewFragment(), RecyclerViewFragment(), RecyclerViewFragment())
}
