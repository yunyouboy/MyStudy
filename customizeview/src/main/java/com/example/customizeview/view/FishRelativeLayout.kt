package com.example.customizeview.view

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.RelativeLayout

/**
 *Author:qyg
 *DATE:2020/7/21 18:00
 *Description：
 **/
class FishRelativeLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
        RelativeLayout(context, attrs, defStyleAttr, defStyleRes) {
    private var mPaint = Paint()

    private lateinit var ivFish: ImageView
    private lateinit var fishDrawable: FishDrawable

    private var touchX: Float = 0f
    private var touchY: Float = 0f

    private var alpha = 100
    private var ripple: Float = 0f
        private set(value) {//field指向属性本身
            alpha = (100 * (1 - value)).toInt()
            field = value
        }

    init {
        ripple = 0f
        // ViewGroup 默认 不执行 onDraw
        setWillNotDraw(false)

        mPaint.apply {
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            strokeWidth = 8f
        }

        var layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        fishDrawable = FishDrawable()
        ivFish = ImageView(context)
        ivFish.apply {
            layoutParams = layoutParams
            //setBackgroundColor(Color.BLUE)
            setImageDrawable(fishDrawable)
        }
        addView(ivFish)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.alpha = alpha
        canvas.drawCircle(touchX, touchY, ripple * 150, mPaint)
        invalidate()
    }

    @SuppressLint("ObjectAnimatorBinding")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        touchX = event.x
        touchY = event.y
        var animator: ObjectAnimator = ObjectAnimator.ofFloat(this, "ripple", 0f, 1f)
        animator.apply {
            duration = 1000
            start()
        }

        return super.onTouchEvent(event)
    }
}