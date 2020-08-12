package cn.readsense.phontoview.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.OnScaleGestureListener
import android.view.View
import android.widget.OverScroller
import cn.readsense.phontoview.util.Utils
import cn.readsense.phontoview.view.PhotoView.PhotoViewConst.OVER_SCALE_FACTOR


/**
 *Author:qyg
 *DATE:2020/8/12 10:11
 *Description：
 **/


class PhotoView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : View(context, attrs, defStyleAttr, defStyleRes) {
    private var bitmap: Bitmap = Utils.getPhoto(resources, Utils.dpToPixel(300f).toInt())
    private var paint: Paint = Paint()

    private var gestureDetector: GestureDetector = GestureDetector(context, PhotoGestureDetector())
    private val scaleGestureDetector: ScaleGestureDetector = ScaleGestureDetector(context, PhotoScaleGestureListener());

    private var originalOffsetX: Float = 0f
    private var originalOffsetY: Float = 0f
    private var smallScale = 0f
    private var bigScale = 0f
    private var miniSmallScale = 0f
    private var maxBigScale = 0f

    private var offsetX: Float = 0f
    private var offsetY: Float = 0f

    private var overScroller = OverScroller(context)

    private var currentScale: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var scaleAnimator: ObjectAnimator? = null

    object PhotoViewConst {
        internal const val OVER_SCALE_FACTOR = 1.5f
    }


    private var isEnlarge: Boolean = false

    init {
        paint.run {
            paint.isAntiAlias = true
            paint.isDither = true
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var smallScale:Float = 0f
        var bigScale:Float = 0f
        if (bitmap.width.toFloat() / bitmap.height > measuredWidth.toFloat() / measuredHeight) {
            // smallScale 是宽全屏
            smallScale = measuredWidth.toFloat() / bitmap.width
            bigScale = measuredHeight.toFloat() / bitmap.height * OVER_SCALE_FACTOR
        } else {
            smallScale = measuredHeight.toFloat() / bitmap.height
            bigScale = measuredWidth.toFloat() / bitmap.width * OVER_SCALE_FACTOR
        }
        miniSmallScale = smallScale
        maxBigScale = bigScale * 3
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val scaleFaction = (currentScale - smallScale) / (bigScale - smallScale)
        // 当前放大比例为small时，scaleFaction = 0，不偏移
        canvas.translate(offsetX * scaleFaction, offsetY * scaleFaction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f);
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        originalOffsetX = (width - bitmap.width) / 2f
        originalOffsetY = (height - bitmap.height) / 2f

        if (bitmap.width.toFloat() / bitmap.height > width.toFloat() / height) {
            // smallScale 是宽全屏
            smallScale = width.toFloat() / bitmap.width
            bigScale = height.toFloat() / bitmap.height * OVER_SCALE_FACTOR
        } else {
            smallScale = height.toFloat() / bitmap.height
            bigScale = width.toFloat() / bitmap.width * OVER_SCALE_FACTOR
        }
        currentScale = smallScale
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // 响应事件以双指缩放优先
        var result = scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            result = gestureDetector.onTouchEvent(event)
        }
        return result
    }

    inner class PhotoGestureDetector : GestureDetector.SimpleOnGestureListener() {

        // 只需要关注 onDown 的返回值即可
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        // 双击的第二次点击down时触发。双击的触发时间 -- 40ms -- 300ms
        override fun onDoubleTap(e: MotionEvent): Boolean {
            isEnlarge = !isEnlarge
            if (isEnlarge) {
                //offsetX = 0f
                //offsetY = 0f
                offsetX = (e.x - width / 2f) - (e.x - width / 2f) * bigScale / smallScale
                offsetY = (e.y - height / 2f) - (e.y - height / 2f) * bigScale / smallScale
                fixOffsets()
                getScaleAnimation().start()
            } else {
                getScaleAnimation().reverse()
            }
            return super.onDoubleTap(e)
        }


        /**
         * 滚动 -- move
         * distanceX X轴上滚动距离，旧位置 - 新位置， distanceX > 0 向左滑动，distanceX < 0 向右滑动
         * distanceY Y轴上滚动距离，旧位置 - 新位置， distanceY > 0 向上滑动，distanceY < 0 向下滑动
         * distanceX / distanceY   理解为画布不动，内容平移。
         * offsetX  /  offsetY     理解为内容不动，画布平移。
         * -> distanceX 与 offsetX 正负相反 -> offsetX < 0 向左滑动，offsetX > 0 向右滑动
         * -> distanceY 与 offsetY 正负相反 -> offsetY < 0 向上滑动，offsetY > 0 向下滑动
         * @param e1        手指按下
         * @param e2        当前的
         * @param distanceX 旧位置 - 新位置
         * @param distanceY
         * @return
         */
        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            if (isEnlarge) {
                offsetX -= distanceX
                offsetY -= distanceY
                fixOffsets()
                invalidate()
            }
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        /**
         * up 惯性滑动 -- 大于50dp/s
         *
         * @param velocityX x轴方向运动速度（像素/s）
         * @param velocityY
         * @return
         */
        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            if (isEnlarge) {
                overScroller.fling(offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                        -(bitmap.width * bigScale - width).toInt() / 2,
                        (bitmap.width * bigScale - width).toInt() / 2,
                        -(bitmap.height * bigScale - height).toInt() / 2,
                        (bitmap.height * bigScale - height).toInt() / 2,
                        100, 100)
                postOnAnimation(FlingRunner())
            }
            return super.onFling(e1, e2, velocityX, velocityY)
        }

        // up 触发，单击或者双击的第一次会触发 --- up时，如果不是双击的第二次点击，不是长按，则触发
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return super.onSingleTapUp(e)
        }

        // 长按 -- 默认300ms触发
        override fun onLongPress(e: MotionEvent?) {
            super.onLongPress(e)
        }

        // 双击的第二次down、move、up 都触发
        override fun onDoubleTapEvent(e: MotionEvent): Boolean {
            return super.onDoubleTapEvent(e)
        }

        // 单击按下时触发，双击时不触发，down，up时都可能触发
        // 延时300ms触发TAP事件
        // 300ms以内抬手 -- 才会触发TAP -- onSingleTapConfirmed
        // 300ms 以后抬手 --- 不是双击不是长按，则触发
        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            return super.onSingleTapConfirmed(e)
        }

        // 延时100ms触发 -- 处理点击效果
        override fun onShowPress(e: MotionEvent) {
            super.onShowPress(e)
        }
    }

    inner class FlingRunner : Runnable {
        override fun run() {
            if (overScroller.computeScrollOffset()) {
                offsetX = overScroller.currX.toFloat()
                offsetY = overScroller.currY.toFloat()
                invalidate();
                // 下一帧动画的时候执行
                postOnAnimation(this)
            }
        }

    }

    /**
     * 双指操作事件
     */
    inner class PhotoScaleGestureListener : OnScaleGestureListener {
        private var initScale = 0f
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            //运行初始时，双指放大、缩小
            if ((currentScale > smallScale && !isEnlarge) || (currentScale == smallScale && !isEnlarge)) isEnlarge = !isEnlarge
            // 缩放因子
            currentScale = initScale * detector.scaleFactor
            currentScale = currentScale.coerceAtMost(maxBigScale)
            currentScale = currentScale.coerceAtLeast(miniSmallScale)
            invalidate()
            return false
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            initScale = currentScale
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector) {}
    }

    private fun getScaleAnimation(): ObjectAnimator {
        if (null == scaleAnimator) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0f)
        }
        scaleAnimator!!.setFloatValues(smallScale, bigScale)
        return scaleAnimator!!
    }

    private fun fixOffsets() {
        offsetX = offsetX.coerceAtMost((bitmap.width * bigScale - width) / 2)     //offsetX>0 处理右滑边界
        offsetX = offsetX.coerceAtLeast(-(bitmap.width * bigScale - width) / 2)   //offsetX<0 处理左滑边界
        offsetY = offsetY.coerceAtMost((bitmap.height * bigScale - height) / 2)   //offsetY>0 处理下滑边界
        offsetY = offsetY.coerceAtLeast(-(bitmap.height * bigScale - height) / 2) //offsetY<0 处理上滑边界
    }
}