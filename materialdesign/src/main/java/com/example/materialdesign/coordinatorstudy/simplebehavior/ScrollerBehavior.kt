package com.example.materialdesign.coordinatorstudy.simplebehavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

class ScrollerBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<RecyclerView>(context, attrs) {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: RecyclerView, dependency: View): Boolean {
        return dependency is TextView
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: RecyclerView, dependency: View): Boolean {
        ViewCompat.offsetTopAndBottom(child, dependency.bottom - child.top)
        return false
    }
}