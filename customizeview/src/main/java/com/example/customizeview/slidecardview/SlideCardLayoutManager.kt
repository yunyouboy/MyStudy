package com.example.customizeview.slidecardview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SlideCardLayoutManager : RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    //布局
    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {

        /*if ((SlideCardCallback.hasNotify && !state.didStructureChange()) || (!SlideCardCallback.hasNotify)) {
            SlideCardCallback.hasNotify = false*/
            // ViewHodler回收
            detachAndScrapAttachedViews(recycler)

            val bottomPosition: Int
            val itemCount = itemCount

            bottomPosition = if (itemCount < CardConfig.MAX_SHOW_COUNT) 0 else itemCount - CardConfig.MAX_SHOW_COUNT // 布局了四张卡片 --- 4，5，6，7

            for (i in bottomPosition until itemCount) {
                // 复用
                val view = recycler.getViewForPosition(i)
                addView(view)

                measureChildWithMargins(view, 0, 0)
                val widthSpace = width - getDecoratedMeasuredWidth(view)
                val heightSpace = height - getDecoratedMeasuredHeight(view)

                // 布局 ---draw -- onDraw ,onDrawOver, onLayout
                layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                        widthSpace / 2 + getDecoratedMeasuredWidth(view),
                        heightSpace / 2 + getDecoratedMeasuredHeight(view))

                val level = itemCount - i - 1
                if (level > 0) {
                    if (level < CardConfig.MAX_SHOW_COUNT - 1) {
                        view.translationY = (CardConfig.TRANS_Y_GAP * level).toFloat()
                        view.scaleX = 1 - CardConfig.SCALE_GAP * level
                        view.scaleY = 1 - CardConfig.SCALE_GAP * level
                    } else {
                        // 最下面的那个view 与前一个view 布局一样
                        view.translationY = (CardConfig.TRANS_Y_GAP * (level - 1)).toFloat()
                        view.scaleX = 1 - CardConfig.SCALE_GAP * (level - 1)
                        view.scaleY = 1 - CardConfig.SCALE_GAP * (level - 1)
                    }
                }
            }
        /* }*/
    }
}