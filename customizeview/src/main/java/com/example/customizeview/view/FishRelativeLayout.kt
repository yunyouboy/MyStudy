package com.example.customizeview.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.RelativeLayout
import kotlin.math.acos
import kotlin.math.atan2
import kotlin.math.sqrt

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
        //invalidate()
    }

    @SuppressLint("ObjectAnimatorBinding")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        touchX = event.x
        touchY = event.y
        var animator: ObjectAnimator = ObjectAnimator.ofFloat(this, "ripple", 0f, 1f)
        animator.apply {
            duration = 1000
            addUpdateListener {
                invalidate()
            }
            start()
        }
        makeTrail()

        return super.onTouchEvent(event)
    }

    private fun makeTrail() {
        // 鱼的重心：相对ImageView坐标
        val fishRelativeMiddle: PointF = fishDrawable.middlePoint
        // 鱼的重心：绝对坐标 --- 起始点
        val fishMiddle = PointF(ivFish.x + fishRelativeMiddle.x, ivFish.y + fishRelativeMiddle.y)
        // 鱼头圆心的坐标 -- 控制点1
        val fishHead = PointF(ivFish.x + fishDrawable.headerPoint.x, ivFish.y + fishDrawable.headerPoint.y)
        // 点击坐标 -- 结束点
        val touch = PointF(touchX, touchY)

        val angle = includeAngle(fishMiddle, fishHead, touch) / 2
        val delta = includeAngle(fishMiddle, PointF(fishMiddle.x + 1, fishMiddle.y), fishHead)

        // 控制点2 的坐标
        val controlPoint = fishDrawable.calculatePoint(fishMiddle, fishDrawable.headerRadius * 1.6f, angle + delta)

        val path = Path()
        path.moveTo(fishMiddle.x - fishRelativeMiddle.x, fishMiddle.y - fishRelativeMiddle.y)
        path.cubicTo(fishHead.x - fishRelativeMiddle.x, fishHead.y - fishRelativeMiddle.y,
                controlPoint.x - fishRelativeMiddle.x, controlPoint.y - fishRelativeMiddle.y,
                touchX - fishRelativeMiddle.x, touchY - fishRelativeMiddle.y)
        val objectAnimator = ObjectAnimator.ofFloat(ivFish, "x", "y", path)
        objectAnimator.duration = 2000
        objectAnimator.apply {

            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    fishDrawable.frequence = 1f
                }

                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    fishDrawable.frequence = 3f
                }
            })
            val pathMeasure = PathMeasure(path, false)
            val tan = FloatArray(2)
            addUpdateListener {
                val fraction = it.animatedFraction
                pathMeasure.getPosTan(pathMeasure.length * fraction, null, tan)
                var angle: Float = Math.toDegrees(atan2(-tan[1].toDouble(), tan[0].toDouble())).toFloat()
                fishDrawable.fishMainAngle = angle
            }
            start()
        }
    }

    private fun includeAngle(O: PointF, A: PointF, B: PointF): Float {
        // cosAOB
        // OA*OB=(Ax-Ox)(Bx-Ox)+(Ay-Oy)*(By-Oy)
        val aob = (A.x - O.x) * (B.x - O.x) + (A.y - O.y) * (B.y - O.y)
        val oaLength = sqrt((A.x - O.x) * (A.x - O.x) + (A.y - O.y) * (A.y - O.y).toDouble()).toFloat()
        // OB 的长度
        val obLength = sqrt((B.x - O.x) * (B.x - O.x) + (B.y - O.y) * (B.y - O.y).toDouble()).toFloat()
        val cosAOB = aob / (oaLength * obLength)

        // 反余弦
        val angleAOB = Math.toDegrees(acos(cosAOB.toDouble())).toFloat()

        // AB连线与X的夹角的tan值 - OB与x轴的夹角的tan值
        val direction = (A.y - B.y) / (A.x - B.x) - (O.y - B.y) / (O.x - B.x)
        return if (direction == 0f) {
            if (aob >= 0) {
                0f
            } else {
                180f
            }
        } else {
            if (direction > 0) {
                -angleAOB
            } else {
                angleAOB
            }
        }
    }
}