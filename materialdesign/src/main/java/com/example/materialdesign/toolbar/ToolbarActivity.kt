package com.example.materialdesign.toolbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityToolbarBinding

/**
 * 1. 按照支持库设置中所述，向您的项目添加 v7 appcompat 支持库。
 * 2. 确保 Activity 可以扩展 AppCompatActivity
 * 3. 在应用清单中，将 <application> 元素设置为使用 appcompat 的其中一个 NoActionBar 主题背景。使用其中一个主题背景可以防止应用使用原生 ActionBar 类提供应用栏
 * 4. 向 Activity 的布局添加一个 Toolbar。例如，以下布局代码会添加一个 Toolbar，并赋予其浮动在 Activity 之上的外观
</application> */
class ToolbarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToolbarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.myToolbar.setTitle("");
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this@ToolbarActivity, R.string.settings, Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_favorite -> {
                Toast.makeText(this@ToolbarActivity, R.string.favorite, Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}