package com.example.materialdesign

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.example.materialdesign.bottomAppBar.BottomAppBarActivity
import com.example.materialdesign.bottomsheets.BottomSheetsActivity
import com.example.materialdesign.cardview.CardViewActivity
import com.example.materialdesign.chips.ChipsActivity
import com.example.materialdesign.coordinator.CoordinatorActivity
import com.example.materialdesign.floatingactionbutton.FloatActionButtonActivity
import com.example.materialdesign.materialbutton.MaterialButtonActivity
import com.example.materialdesign.materialtext.TextInputActivity
import com.example.materialdesign.navigation.CloudMusicActivity
import com.example.materialdesign.nestedscroll.activity.NestScrollActivity
import com.example.materialdesign.tab.TabActivity
import com.example.materialdesign.toolbar.ToolbarActivity
import com.example.materialdesign.translucent.QQMusicActivity
import com.example.materialdesign.viewpager2.ViewPager2Activity
import com.example.materialdesign.z.ZActivity

class MainFragment : ListFragment() {
    private lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val array = arrayOf<String>(
                "ToolBar",  //0
                "FloatActionButton与Snackbar",  //1
                "MaterialButtonActivity",  //2
                "DrawLayout与NaviagtionView",  //3
                "TextInputActivity",  //4
                "CardView",  //5
                "TabLayout",  //6
                "CoordinatorLayout",  //7
                "Translucent",  //8
                "BottomSheets",  //9
                "ViewPager2Activity",  //10
                "BottomAppBarActivity",  //11
                "ChipsActivity",  //12
                "ZActivity",  //13
                "NestScrollActivity")
        arrayAdapter = ArrayAdapter(view.context, R.layout.simple_list_item_1, array)
        listAdapter = arrayAdapter
    }

    override fun onListItemClick(listView: ListView, view: View, position: Int, id: Long) {
        super.onListItemClick(listView, view, position, id)
        val item = arrayAdapter.getItem(position)
        Toast.makeText(activity, item, Toast.LENGTH_LONG).show()
        val gotoAct: Intent?
        when (position) {
            0 -> {
                gotoAct = Intent(activity, ToolbarActivity::class.java)
            }
            1 -> {
                gotoAct = Intent(activity, FloatActionButtonActivity::class.java)
            }
            2 -> {
                gotoAct = Intent(activity, MaterialButtonActivity::class.java)
            }
            3 -> {
                gotoAct = Intent(activity, CloudMusicActivity::class.java)
            }
            4 -> {
                gotoAct = Intent(activity, TextInputActivity::class.java)
            }
            5 -> {
                gotoAct = Intent(activity, CardViewActivity::class.java)
            }
            6 -> {
                gotoAct = Intent(activity, TabActivity::class.java)
            }
            7 -> {
                gotoAct = Intent(activity, CoordinatorActivity::class.java)
            }
            8 -> {
                gotoAct = Intent(activity, QQMusicActivity::class.java)
            }
            9 -> {
                gotoAct = Intent(activity, BottomSheetsActivity::class.java)
            }
            10 -> {
                gotoAct = Intent(activity, ViewPager2Activity::class.java)
            }
            11 -> {
                gotoAct = Intent(activity, BottomAppBarActivity::class.java)
            }
            12 -> {
                gotoAct = Intent(activity, ChipsActivity::class.java)
            }
            13 -> {
                gotoAct = Intent(activity, ZActivity::class.java)
            }
            14 -> {
                gotoAct = Intent(activity, NestScrollActivity::class.java)
            }
            else -> {
                gotoAct = null
            }
        }
        startActivity(gotoAct!!)
    }

    companion object {
        fun newIntance(): Fragment {
            return MainFragment()
        }
    }
}