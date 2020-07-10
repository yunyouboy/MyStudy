package cn.readsense.skinapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cn.readsense.skinapp.fragment.MusicFragment
import cn.readsense.skinapp.fragment.RadioFragment
import cn.readsense.skinapp.fragment.VideoFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //控件的收集在这里完成
        setContentView(R.layout.activity_main)
        val list: MutableList<Fragment> = ArrayList<Fragment>()
        list.add(MusicFragment())
        list.add(VideoFragment())
        list.add(RadioFragment())
        val listTitle: MutableList<String> = ArrayList()
        listTitle.add("音乐")
        listTitle.add("视频")
        listTitle.add("电台")
        val myFragmentPagerAdapter = MyFragmentPagerAdapter(supportFragmentManager, list, listTitle)
        viewPager.adapter = myFragmentPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    /**
     * 进入换肤
     *
     * @param view
     */
    fun skinSelect(view: View) {
        startActivity(Intent(this, SkinActivity::class.java))
    }
}