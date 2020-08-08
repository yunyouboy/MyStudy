package com.example.customizeview.slidecardview2

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.customizeview.slidecardview.CardConfig
import kotlin.math.sqrt

class SlideCardCallback(var mRecyclerView: RecyclerView, var data: ArrayList<SlideCardBean>) : ItemTouchHelper.SimpleCallback(0, 15) {

    //拖拽
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    //滑动
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        var remove: SlideCardBean = data.removeAt(viewHolder.layoutPosition)
        data.add(0, remove)
        mRecyclerView.adapter?.notifyDataSetChanged() // onMeasure, onlayout
    }

    //onDraw()中调用
    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val maxDistance = recyclerView.width * 0.1f.toDouble()
        val distance = sqrt((dX * dX + dY * dY).toDouble())
        var fraction = distance / maxDistance

        if (fraction > 1) {
            fraction = 1.0
        }

        // 显示的个数  4个
        val itemCount = recyclerView.childCount

        for (i in 0 until itemCount) {
            val view = recyclerView.getChildAt(i)
            val level = itemCount - i - 1
            if (level > 0) {
                if (level < CardConfig.MAX_SHOW_COUNT - 1) {
                    view.translationY = (CardConfig.TRANS_Y_GAP * level - fraction * CardConfig.TRANS_Y_GAP).toFloat()
                    view.scaleX = (1 - CardConfig.SCALE_GAP * level + fraction * CardConfig.SCALE_GAP).toFloat()
                    view.scaleY = (1 - CardConfig.SCALE_GAP * level + fraction * CardConfig.SCALE_GAP).toFloat()
                }
            }
        }
    }

    override fun getAnimationDuration(recyclerView: RecyclerView, animationType: Int, animateDx: Float, animateDy: Float): Long {
        return 200L
    }
}