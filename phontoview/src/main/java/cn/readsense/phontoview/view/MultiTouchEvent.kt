package cn.readsense.phontoview.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import cn.readsense.phontoview.util.Utils

/**
 * 最后一个按下的手指处理事件
 */
class MultiTouchEvent @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    companion object {
        private val IMAGE_WIDTH: Float = Utils.dpToPixel(300f)
    }

    private var bitmap: Bitmap = Utils.getPhoto(resources, IMAGE_WIDTH.toInt())
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 手指滑动偏移值
    private var offsetX = 0f
    private var offsetY = 0f

    // 按下时的x,y坐标
    private var downX = 0f
    private var downY = 0f

    // 上一次的偏移值
    private var lastOffsetX = 0f
    private var lastOffsetY = 0f

    // 当前按下的pointId
    private var currentPointId = 0


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                lastOffsetX = offsetX
                lastOffsetY = offsetY

                // id
                currentPointId = 0
            }
            MotionEvent.ACTION_MOVE -> {
                // 通过id 拿index
                val index = event.findPointerIndex(currentPointId)
                // event.getX()默认 index = 0的坐标 --- move操作的是后按下的手指
                offsetX = lastOffsetX + event.getX(index) - downX
                offsetY = lastOffsetY + event.getY(index) - downY
                invalidate()
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                // 获取当前按下的index
                val actionIndex = event.actionIndex
                // 通过index 拿 id
                currentPointId = event.getPointerId(actionIndex)
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
                lastOffsetX = offsetX
                lastOffsetY = offsetY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                // 获取当前抬起的index
                var upIndex = event.actionIndex
                // 获取当前抬起的id
                val pointerId = event.getPointerId(upIndex)
                // 非活跃手指的抬起不用处理
                if (pointerId == currentPointId) {
                    if (upIndex == event.pointerCount - 1) {
                        upIndex = event.pointerCount - 2
                    } else {
                        upIndex++
                        //upIndex = event.getPointerCount() - 1;
                    }
                    currentPointId = event.getPointerId(upIndex)
                    downX = event.getX(upIndex)
                    downY = event.getY(upIndex)
                    lastOffsetX = offsetX
                    lastOffsetY = offsetY
                }
            }
        }
        return true
    }

}