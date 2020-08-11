package com.example.materialdesign.coordinatorstudy.behavior

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.materialdesign.coordinatorstudy.view.DependedView

class BrotherChameleonBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {
    private val mArgbEvaluator = ArgbEvaluator()
    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is DependedView
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val color = mArgbEvaluator.evaluate(dependency.y / parent.height, Color.WHITE, Color.BLACK) as Int
        child.setBackgroundColor(color)
        return false
    }
}